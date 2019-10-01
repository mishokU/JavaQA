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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.username_field) EditText mUserName;
    @BindView(R.id.password_field) EditText mPassword;
    @BindView(R.id.email_field) EditText mEmail;
    @BindView(R.id.repeat_password_field) EditText mRepeatPassword;
    @BindView(R.id.registration_button) Button mRegistrationButton;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    private FirebaseAuth mAuth;
    private ProgressDialog progress;
    private DatabaseReference firebaseDatabase;
    private String user_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        setUpRegistrationButton();
        setUpToolbar();
    }

    private void setUpRegistrationButton() {
        mRegistrationButton.setOnClickListener(this);
        RxView.clicks(mRegistrationButton)
            .combineLatest(
                RxTextView.textChanges(mUserName),
                RxTextView.textChanges(mPassword),
                RxTextView.textChanges(mRepeatPassword),
                RxTextView.textChanges(mEmail),
                (username, password, repeat_password, email) ->
                    username.length() > 0 && password.length() > 0 && repeat_password.length() > 0 && email.length() > 0
            ).subscribe(mRegistrationButton::setEnabled);
    }

    public void registration(){
        mAuth.createUserWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString()).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                assert firebaseUser != null;
                user_id = firebaseUser.getUid();
                firebaseDatabase = FirebaseDatabase.getInstance().getReference();
                
                createStatisticData();
                createAccount();

            } else {
                progress.hide();
                Toast.makeText(this,"Can not register with email and password!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createStatisticData() {
        DatabaseReference statisticReference = firebaseDatabase.child("Users").child(user_id).child("statistic");
        HashMap<String, Integer> statisticMap = new HashMap<>();

        statisticMap.put("wins", 0);
        statisticMap.put("loses", 0);
        statisticMap.put("draws", 0);

        statisticMap.put("games", 0);
        statisticMap.put("averageScore", 0);
        statisticMap.put("gamesWithOutLoses", 0);

        statisticMap.put("javaCoreFullGames", 0);
        statisticMap.put("buildToolsFullGames", 0);
        statisticMap.put("vscFullGames", 0);
        statisticMap.put("databasesFullGames", 0);

        statisticMap.put("javaCoreWins", 0);
        statisticMap.put("buildToolsWins", 0);
        statisticMap.put("vscWins", 0);
        statisticMap.put("databasesWins", 0);

        statisticReference.setValue(statisticMap);
    }

    private void createAccount() {
        DatabaseReference userDataReference = firebaseDatabase.child("Users").child(user_id).child("userData");

        HashMap<String, String> newUserMap = new HashMap<>();
        newUserMap.put("username",mUserName.getText().toString());
        newUserMap.put("email",mEmail.getText().toString());
        newUserMap.put("password",mPassword.getText().toString());
        newUserMap.put("imageURL", "default");
        newUserMap.put("level","1");

        userDataReference.setValue(newUserMap).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                progress.dismiss();
                launchActivity();
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
