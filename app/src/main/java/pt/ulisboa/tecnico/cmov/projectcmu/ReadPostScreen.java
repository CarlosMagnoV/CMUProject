package pt.ulisboa.tecnico.cmov.projectcmu;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReadPostScreen extends AppCompatActivity {

    DatabaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_post_screen);

        mHelper = DatabaseHelper.getInstance(this);

        final Context c = this;

        Runnable updateUI = new Runnable() {
            @Override
            public void run() {
                Intent i = getIntent();
                String[] noteTitle = new String[]{(String) i.getExtras().get("PostTitle")};


                SQLiteDatabase db = mHelper.getReadableDatabase();
                Cursor cursor = db.query(PostContract.PostEntry.TABLE,
                        new String[]{PostContract.PostEntry.COL_TASK_BODY},
                        "title=? ", noteTitle, null, null, null);

                TextView Title = (TextView)findViewById(R.id.Title);
                TextView Content = (TextView)findViewById(R.id.note_content);
                Title.setText(noteTitle[0]);
                while(cursor.moveToNext()) {
                    Content.setText(cursor.getString(0));
                }
                cursor.close();


                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(c, "Interface Updated asynchronously!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        Thread t = new Thread(updateUI, "Thread");
        t.start();
    }

    public void back(View view){
        finish();
    }

    public void deletePost(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.Title);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(PostContract.PostEntry.TABLE,
                PostContract.PostEntry.COL_TASK_TITLE + " = ?",
                new String[]{task});
        db.close();
        finish();
    }

}

