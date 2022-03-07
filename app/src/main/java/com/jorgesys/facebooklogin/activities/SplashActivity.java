package com.jorgesys.facebooklogin.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.share.widget.ShareDialog;
import com.jorgesys.facebooklogin.R;
        /*
        Login for android using Android SDK.
        https://developers.facebook.com/docs/facebook-login/android
        app settings
        https://developers.facebook.com/apps/<Facebook app id>/settings/basic/
        https://developers.facebook.com/apps/
         */

public class SplashActivity extends AppCompatActivity {

    private final static String TAG = "FacebookLogin";
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private LoginManager manager;
    private Button compartirBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 2000);

    }

}