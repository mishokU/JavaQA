package com.example.javaqa.DataBase.Authentication.Authentication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javaqa.activities.MainActivity;
import com.example.javaqa.ActivityUtils.LaunchActivityHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn implements UserAuthentication {

    private FirebaseAuth mFirebaseAuth;
    private ProgressDialog progress;
    private Activity activity;
    private String mUserName;
    private String mPassword;
    private LaunchActivityHelper launchActivityHelper;

    public SignIn(Activity activity){
        this.activity = activity;
    }

    public boolean checkOnSignIn(){
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        if(currentUser == null) {
            return true;
        }
        return false;
    }

    @Override
    public void authentication() {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean checkData(EditText user_name, EditText password,EditText repeat_password, EditText email) {
        mUserName = user_name.getText().toString();
        mPassword = password.getText().toString();
        if(TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassword)) {
            Toast.makeText(activity,"All fields are required!",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void startProgressDialog(Context context) {
        progress = new ProgressDialog(context);
        progress.setTitle("Авторизация...");
        progress.setMessage("Подождите, пока мы входим в Ваш аккаунт :)");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

    @Override
    public void toAccount() {
        launchActivityHelper = new LaunchActivityHelper();
        mFirebaseAuth.signInWithEmailAndPassword(mUserName, mPassword)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        progress.hide();
                        launchActivityHelper.launchActivity(activity, MainActivity.class, Intent.FLAG_ACTIVITY_NO_HISTORY);
                        activity.finish();
                    } else {
                        progress.hide();
                        Toast.makeText(activity, "Не удалось войти.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
