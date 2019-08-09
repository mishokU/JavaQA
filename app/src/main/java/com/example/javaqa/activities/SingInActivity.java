package com.example.javaqa.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.javaqa.DataBase.Authentication.SignIn;
import com.example.javaqa.DataBase.Authentication.UserAuthentication;
import com.example.javaqa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingInActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.username_field) public EditText mUserName;
    @BindView(R.id.password_field) public EditText mPassword;
    @BindView(R.id.sign_button) public Button mSignInButton;
    @BindView(R.id.toolbar) public Toolbar mToolbar;

    private UserAuthentication mSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        ButterKnife.bind(this);
        mSignIn = new SignIn(this);
        mSignInButton.setOnClickListener(this);
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
        if(v == mSignInButton){
            if(mSignIn.checkData(mUserName, mPassword,null,null)){
                mSignIn.startProgressDialog(this);
                mSignIn.toAccount();
            }
        }
    }
}
