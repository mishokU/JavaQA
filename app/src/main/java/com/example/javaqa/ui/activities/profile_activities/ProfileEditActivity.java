package com.example.javaqa.ui.activities.profile_activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.javaqa.R;
import com.example.javaqa.models.UserMainData;
import com.example.javaqa.viewmodels.UserViewModel;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;


public class ProfileEditActivity extends AppCompatActivity implements View.OnClickListener {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.user_name_field) EditText userNameField;
  @BindView(R.id.email_field) EditText emailField;
  @BindView(R.id.save_profile_data_button) Button saveDataButton;
  @BindView(R.id.profile_user_image) ImageView profileImage;

  private UserViewModel mUserViewModel;
  private UserMainData mUserMainData;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_profile);
    ButterKnife.bind(this);

    setUserViewModel();
    setUpToolbar();
    setOnClicks();
    checkSaveButton();
  }

  private void setOnClicks() {
    saveDataButton.setOnClickListener(this);
  }

  private void setUserViewModel() {
    mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    mUserViewModel.getUserMainData().observe(this, userMainData -> {
      mUserMainData = userMainData;
      fillMainData(mUserMainData);
    });
  }

  private void fillMainData(UserMainData userMainData) {
    userNameField.setText(userMainData.getUsername());
    emailField.setText(userMainData.getEmail());
    //Picasso.get().load(userMainData.getImageURL()).placeholder(R.drawable.mytest).into(profileImage);
  }

  private void checkSaveButton() {
    RxView.clicks(saveDataButton)
        .combineLatest(
            RxTextView.textChanges(userNameField),
            RxTextView.textChanges(emailField),
            (login, email) -> login.length() > 0 && email.length() > 0
        ).subscribe(saveDataButton::setEnabled);
  }


  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null){
      getSupportActionBar().setTitle("Редактировать Профиль");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public void onClick(View view) {
    if(view == saveDataButton){
      mUserMainData.setEmail(emailField.getText().toString());
      mUserMainData.setUsername(userNameField.getText().toString());
      mUserViewModel.setUserMainData(mUserMainData);
      Toast.makeText(this,"Data changed", Toast.LENGTH_SHORT).show();
    }
  }
}
