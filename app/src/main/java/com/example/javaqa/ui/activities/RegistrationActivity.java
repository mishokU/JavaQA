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
import androidx.lifecycle.ViewModelProviders;

import com.example.javaqa.R;
import com.example.javaqa.models.GameStatistics;
import com.example.javaqa.models.UserMainData;
import com.example.javaqa.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.password_field) EditText mPassword;
    @BindView(R.id.email_field) EditText mEmail;
    @BindView(R.id.repeat_password_field) EditText mRepeatPassword;
    @BindView(R.id.registration_button) Button mRegistrationButton;
    @BindView(R.id.username_field) EditText userName;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    private ProgressDialog progress;
    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        setUpRegistrationButton();
        setUpToolbar();
    }

    private void setUpRegistrationButton() {
        mRegistrationButton.setOnClickListener(this);
        RxView.clicks(mRegistrationButton).combineLatest(
                RxTextView.textChanges(userName),
                RxTextView.textChanges(mPassword),
                RxTextView.textChanges(mRepeatPassword),
                RxTextView.textChanges(mEmail),
                (username, password, repeat_password, email) ->
                  username.length() > 0 && password.length() > 0 && repeat_password.length() > 0 && email.length() > 0
            ).subscribe(mRegistrationButton::setEnabled);
    }

    public void registration(){
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        FirebaseAuth firebaseAuth = mUserViewModel.getAuth();
        firebaseAuth.createUserWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString())
            .addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    mUserViewModel.updateAuth();
                    UserMainData userMainData = new UserMainData();

                    userMainData.setUsername(userName.getText().toString());
                    userMainData.setEmail(mEmail.getText().toString());
                    userMainData.setPassword(mPassword.getText().toString());
                    userMainData.setLevel("0");
                    userMainData.setImageURL("default");

                    mUserViewModel.createUser(userMainData);
                    mUserViewModel.setGameStatistic(new GameStatistics());

                    progress.hide();
                    launchActivity();
                } else {
                    progress.hide();
                    Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void launchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    private void setUpToolbar() {
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null ) {
            getSupportActionBar().setTitle("Registration");
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

    public void startProgressDialog() {
        progress = new ProgressDialog(this);
        progress.setTitle("Регистрируем Вас.");
        progress.setMessage("Подождите, пока мы создаем Вам аккаунт :)");
        progress.setCanceledOnTouchOutside(false);
        progress.show();
    }

    @Override
    public void onClick(View view) {
        if(view == mRegistrationButton) {
            startProgressDialog();
            registration();
        }
    }
}
