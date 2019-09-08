package com.example.javaqa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javaqa.ActivityUtils.LaunchActivityHelper;
import com.example.javaqa.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.login) Button mLoginButton;
    @BindView(R.id.login_with_facebook) Button mLoginWithFacebookButton;
    @BindView(R.id.language_swapper) CircleImageView mLanguageSwapper;
    @BindView(R.id.create_account) Button mCreateAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        setOnClicks();
    }

    private void setOnClicks() {
        mLoginButton.setOnClickListener(this);
        mLoginWithFacebookButton.setOnClickListener(this);
        mLanguageSwapper.setOnClickListener(this);
        mCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mLoginButton) {
            launchActivity(SingInActivity.class);
        } else if(v == mLoginWithFacebookButton) {
            //Facebook API
        } else if(v == mCreateAccount) {
            launchActivity(RegistrationActivity.class);
        } else if(v == mLanguageSwapper) {
            //Create language dialog swapper
        }
    }

    private void launchActivity(Class activity){
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0,0);
    }
}
