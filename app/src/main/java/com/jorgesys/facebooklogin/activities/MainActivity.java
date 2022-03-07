package com.jorgesys.facebooklogin.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.LoginStatusCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.jorgesys.facebooklogin.R;

import java.util.Arrays;

        /*
        Login for android using Android SDK.
        https://developers.facebook.com/docs/facebook-login/android
        app settings
        https://developers.facebook.com/apps/331877241457/settings/basic/
        https://developers.facebook.com/apps/
         */

        /* Configure your Facebook App id into your strings.xml file.

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "FacebookLogin";
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private LoginManager manager;
    private Button compartirBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shareDialog = new ShareDialog(this);

        compartirBtn = findViewById(R.id.compartir_btn);
        compartirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //10. Comprobar el estado del inicio de sesión
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

                Log.i(TAG, "isLoggedIn : " + isLoggedIn);
                if (isLoggedIn) {
                    Log.i(TAG, "call postFacebook()");
                    postFacebook();
                    finish();
                } else {
                    Log.i(TAG, "call manager.logIn()");
                    manager.logIn(MainActivity.this, Arrays.asList("public_profile"/*, "user_friends" : invalid scope*/));
                }
            }
        });

        //11. Activar el inicio de sesión rápido
       /* LoginManager.getInstance().retrieveLoginStatus(this, new LoginStatusCallback() {
            @Override
            public void onCompleted(AccessToken accessToken) {
                // User was previously logged in, can log them in directly here.
                // If this callback is called, a popup notification appears that says
                // "Logged in as <User Name>"
                Log.i(TAG, "retrieveLoginStatus onCompleted() getApplicationId " + accessToken.getApplicationId());
                Log.i(TAG, "retrieveLoginStatus onCompleted() getUserId " + accessToken.getUserId());
                Log.i(TAG, "retrieveLoginStatus onCompleted() getToken " + accessToken.getToken());
            }

            @Override
            public void onFailure() {
                Log.e(TAG, "retrieveLoginStatus onFailure() No access token could be retrieved for the user.");
            }

            @Override
            public void onError(Exception exception) {
                Log.e(TAG, "retrieveLoginStatus onError() exception " + exception.getMessage());
            }
        });*/

        //9. Registrar una devolución de llamada
        facebookLoginCallback();

    }

    private void postFacebook() {
        Log.i(TAG, "postFacebook()");
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            Log.i(TAG, "ShareDialog.canShow(()");
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("https://es.stackoverflow.com/"))
                    .build();
            shareDialog.show(linkContent);
        }else{
            Log.e(TAG, "ShareDialog.canShow(ShareLinkContent.class) is FALSE!");
        }
    }

    private void facebookLoginCallback() {
        Log.i(TAG, "facebookLoginCallback()");
        /*------------------------- Call authentication -------------------------*/
        callbackManager = CallbackManager.Factory.create();
        manager = LoginManager.getInstance();
        manager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                postFacebook();
                finish();
                Log.i(TAG, "facebookLoginCallback() onSuccess() getUserId: " + loginResult.getAccessToken().getUserId());
                Log.i(TAG, "facebookLoginCallback() onSuccess() getApplicationId: " + loginResult.getAccessToken().getApplicationId());
                Log.i(TAG, "facebookLoginCallback() onSuccess() getToken: " + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onError(FacebookException exception) {
                com.facebook.AccessToken.setCurrentAccessToken(null); //Delete token!
                Log.e(TAG, "manager.registerCallback onError()! " + exception.getMessage());
            }

            @Override
            public void onCancel() {
                com.facebook.AccessToken.setCurrentAccessToken(null); //Delete token!
                Log.e(TAG, "manager.registerCallback onCancel()! ");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "callbackManager.onActivityResult() requestCode " + requestCode + " resultCode " + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }

}