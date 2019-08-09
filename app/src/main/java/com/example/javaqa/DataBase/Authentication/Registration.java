package com.example.javaqa.DataBase.Authentication;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration implements UserAuthentication {

    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private ProgressDialog progress;
    private Activity activity;

    private String mUserName;
    private String mPassword;
    private String mRepeatPassword;
    private String mEmail;

    public Registration(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void authentication() {
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void toAccount() {
        mAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                assert firebaseUser != null;
                String user_id = firebaseUser.getUid();

                mReference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                createAccount();

            } else {
                progress.hide();
                Toast.makeText(activity,"Can not register with email and password!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAccount() {
        HashMap<String, String> newUserMap = new HashMap<>();
        newUserMap.put("username",mUserName);
        newUserMap.put("email",mEmail);
        newUserMap.put("imageUrl", "default");

        mReference.setValue(newUserMap).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                progress.dismiss();
                new LaunchActivityHelper(activity, MainActivity.class, Intent.FLAG_ACTIVITY_NO_HISTORY);
            }
        });
    }

    @Override
    public boolean checkData(EditText user_name, EditText password, EditText repeat_password, EditText email) {

        mUserName = user_name.getText().toString();
        mEmail = email.getText().toString();
        mPassword = password.getText().toString();
        mRepeatPassword = repeat_password.getText().toString();

        if(TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mEmail) || TextUtils.isEmpty(mPassword)){
            Toast.makeText(activity, "Заполните Все Поля!", Toast.LENGTH_SHORT).show();
        } else if(!mPassword.equals(mRepeatPassword)){
            Toast.makeText(activity, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void startProgressDialog(Context context) {
        progress = new ProgressDialog(context);
        progress.setTitle("Регистрируем Вас.");
        progress.setMessage("Подождите, пока мы создаем Вам аккаунт :)");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }
}
