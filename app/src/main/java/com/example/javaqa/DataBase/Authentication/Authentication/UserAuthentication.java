package com.example.javaqa.DataBase.Authentication.Authentication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.EditText;

public interface UserAuthentication {

    void authentication();

    void toAccount();

    boolean checkData(EditText user_name, EditText password,EditText repeat_password, EditText email);

    void startProgressDialog(Context context);
}
