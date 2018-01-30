package automate.capstone.feeder;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity implements AsyncResponse {
    private static int SPLASH_TIME_OUT = 1000; //4 seconds
    DatabaseHelper dh = new DatabaseHelper(this);
    String logs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DatabaseHelper dh = new DatabaseHelper(this);
        String type="view logs";
        dh.execute(type,null,null);
        dh.delegate = this;
        logs = dh.delegate.toString();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    public void processFinish(String output) {
        Store.logs = output;
    }
}
