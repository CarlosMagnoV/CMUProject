package pt.ulisboa.tecnico.cmov.projectcmu;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewPostScreen extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post_screen);
        this.dbHelper = DatabaseHelper.getInstance(this);
    }

    public void okPost(View v){
        EditText edBody = (EditText) findViewById(R.id.body_text);
        EditText edTitle = (EditText) findViewById(R.id.title_text);
        String strBody = edBody.getText().toString();
        String strTitle = edTitle.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PostContract.PostEntry.COL_TASK_TITLE, strTitle);
        values.put(PostContract.PostEntry.COL_TASK_BODY, strBody);
        db.insertWithOnConflict(PostContract.PostEntry.TABLE,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        finish();

    }
}
