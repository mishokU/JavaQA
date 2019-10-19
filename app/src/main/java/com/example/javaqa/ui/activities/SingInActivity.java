package com.example.javaqa.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.javaqa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingInActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.username_field) public EditText mUserName;
    @BindView(R.id.password_field) public EditText mPassword;
    @BindView(R.id.sign_button) public Button mSignInButton;
    @BindView(R.id.toolbar) public Toolbar mToolbar;

    private FirebaseAuth mFirebaseAuth;
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        initFireBase();
        setUpToolbar();
    }

    private void initFireBase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        mSignInButton.setOnClickListener(this);
        RxView.clicks(mSignInButton)
            .combineLatest(
                RxTextView.textChanges(mUserName),
                RxTextView.textChanges(mPassword),
                (login, password) -> login.length() > 0 && password.length() > 0
            ).subscribe(mSignInButton::setEnabled);
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null ) {
            getSupportActionBar().setTitle("Sign In");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
            startProgressDialog();
            toAccount();
        }
    }

    private void launchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    public void toAccount() {
        mFirebaseAuth.signInWithEmailAndPassword(mUserName.getText().toString(), mPassword.getText().toString())
            .addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    progress.hide();
                    launchActivity();
                } else {
                    progress.hide();
                    Toast.makeText(this, "Не удалось войти.", Toast.LENGTH_SHORT).show();
                }
            });
    }

    public void startProgressDialog() {
        progress = new ProgressDialog(this);
        progress.setTitle("Авторизация...");
        progress.setMessage("Подождите, пока мы входим в Ваш аккаунт :)");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }
}
