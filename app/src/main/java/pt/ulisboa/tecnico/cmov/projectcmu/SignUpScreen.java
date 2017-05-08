package pt.ulisboa.tecnico.cmov.projectcmu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pt.ulisboa.tecnico.cmov.projectcmu.util.StatusTracker;

/**
 * Created by cmagn on 08/05/2017.
 */

public class SignUpScreen extends Activity {
    private String mActivityName;
    private StatusTracker mStatusTracker = StatusTracker.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);
        mActivityName = "sign_up_screen";
        mStatusTracker.setStatus(mActivityName, "onCreate");
        Button p1_button = (Button)findViewById(R.id.SUbackButton);
        p1_button.setText("Back to Login");
        //Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStatusTracker.setStatus(mActivityName, "onStart");
        //Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mStatusTracker.setStatus(mActivityName, "onRestart");
        //Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStatusTracker.setStatus(mActivityName, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        mStatusTracker.setStatus(mActivityName, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        mStatusTracker.setStatus(mActivityName, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStatusTracker.setStatus(mActivityName, "onDestroy");
        mStatusTracker.clear();
    }

    public void startActivityLogin(View v) {
        Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
        startActivity(intent);
    }

}
