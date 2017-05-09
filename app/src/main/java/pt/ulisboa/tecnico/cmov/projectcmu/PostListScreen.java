package pt.ulisboa.tecnico.cmov.projectcmu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostListScreen extends AppCompatActivity {

    private static final String TAG = "PostMenuScreen";
    DatabaseHelper mHelper;
    private ArrayAdapter<String> mAdapter;
    private ListView mTaskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list_screen);

        mTaskListView = (ListView) findViewById(R.id.posts_list);
        mHelper = DatabaseHelper.getInstance(this);
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(PostContract.PostEntry.TABLE,
                new String[]{PostContract.PostEntry._ID, PostContract.PostEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(PostContract.PostEntry.COL_TASK_TITLE);
            Log.d(TAG, "Post: " + cursor.getString(idx));
        }
        cursor.close();
        db.close();
        updateUI();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostListScreen.this, NewPostScreen.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI() {
        ArrayList<String> notesList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(PostContract.PostEntry.TABLE,
                new String[]{PostContract.PostEntry._ID,PostContract.PostEntry.COL_TASK_TITLE},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(PostContract.PostEntry.COL_TASK_TITLE);
            notesList.add(cursor.getString(idx));
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.posts_list,
                    R.id.post_title,
                    notesList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(notesList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

    public void viewPost(View view){
        Intent intent = new Intent(PostListScreen.this, ReadPostScreen.class);
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.post_title);

        Bundle b = new Bundle();
        String str = String.valueOf(taskTextView.getText());
        b.putString("PostTitle", str);
        intent.putExtras(b);
        startActivity(intent);
    }



}
