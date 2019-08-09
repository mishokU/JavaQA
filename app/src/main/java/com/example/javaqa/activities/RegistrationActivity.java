package com.example.javaqa.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.javaqa.DataBase.Authentication.Registration;
import com.example.javaqa.DataBase.Authentication.UserAuthentication;
import com.example.javaqa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.username_field) EditText mUserName;
    @BindView(R.id.password_field) EditText mPassword;
    @BindView(R.id.email_field) EditText mEmail;
    @BindView(R.id.repeat_password_field) EditText mRepeatPassword;
    @BindView(R.id.registration_button) Button mRegistrationButton;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    private UserAuthentication mRegistration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);
        ButterKnife.bind(this);
        mRegistration = new Registration(this);
        mRegistrationButton.setOnClickListener(this);
        setUpToolbar();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    public void onClick(View v) {
        if(v == mRegistrationButton) {
            if(mRegistration.checkData(mUserName,mPassword,mRepeatPassword,mEmail)){
                mRegistration.startProgressDialog(this);
                mRegistration.toAccount();
            }
        }
    }
}
