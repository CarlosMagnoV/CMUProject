package pt.ulisboa.tecnico.cmov.projectcmu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pt.ulisboa.tecnico.cmov.projectcmu.util.StatusTracker;

/**
 * Created by cmagn on 08/05/2017.
 */

public class LoginScreen extends Activity {
    private String mActivityName;
    private StatusTracker mStatusTracker = StatusTracker.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        mActivityName = "login_screen";
        mStatusTracker.setStatus(mActivityName, "onCreate");
        Button p1_button = (Button)findViewById(R.id.LsignUpButton);
        p1_button.setText("Sign Up");
        //Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //mStatusTracker.setStatus(mActivityName, "onStart");
        //Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //mStatusTracker.setStatus(mActivityName, "onRestart");
        //Utils.printStatus(mStatusView, mStatusAllView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mStatusTracker.setStatus(mActivityName, "onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        //mStatusTracker.setStatus(mActivityName, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        //mStatusTracker.setStatus(mActivityName, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mStatusTracker.setStatus(mActivityName, "onDestroy");
        //mStatusTracker.clear();
    }

    public void doLogin(View view) {

        String username = null;
        String password = null;
        try {
            username = String.valueOf(((TextView) this.findViewById(R.id.SUusername)).getText());
            password = String.valueOf(((TextView) this.findViewById(R.id.SUpassword)).getText());
        }
        catch(NullPointerException e){
            Toast.makeText(this, "Username or password empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainMenuScreen.class);
        startActivity(intent);
    }

    public void startActivitySignUp(View view) {
        Intent intent = new Intent(this, SignUpScreen.class);
        startActivity(intent);
    }
}