package com.example.javaqa.ui.activities.profile_activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.javaqa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class ProfileEditActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.user_name_field) EditText userNameField;
  @BindView(R.id.email_field) EditText emailField;
  @BindView(R.id.save_profile_data_button) Button saveDataButton;
  @BindView(R.id.profile_user_image) ImageView profileImage;

  private DatabaseReference databaseReference;
  private FirebaseUser firebaseUser;
  private FirebaseAuth firebaseAuth;
  private FirebaseStorage firebaseStorage;
  private String userId;

  private ObservableTransformer verifyEmailPattern;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_profile);
    ButterKnife.bind(this);

    initFirebase();
    setRxBinding();
    getInformationAboutUser();
    setUpToolbar();
    setOnClicks();
    setUpSaveButton();
  }

  private void initFirebase() {
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    userId = firebaseUser.getUid();
    //Get to current user information in database;
    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("userData");
    firebaseStorage = FirebaseStorage.getInstance();
  }

  private void setRxBinding() {
    RxTextView.afterTextChangeEvents(emailField)
        .map(s -> s.toString())
        .debounce(400, TimeUnit.MILLISECONDS)
        .filter(f -> !TextUtils.isEmpty(f))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();
  }

  private void getInformationAboutUser() {
    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        String userName = dataSnapshot.child("username").getValue().toString();
        String emailAddress = dataSnapshot.child("email").getValue().toString();
        String imageURL = dataSnapshot.child("imageURL").getValue().toString();

        userNameField.setText(userName);
        emailField.setText(emailAddress);
        Picasso.get().load(imageURL).placeholder(R.drawable.mytest).into(profileImage);

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void setUpSaveButton() {
    RxView.clicks(saveDataButton)
        .combineLatest(
            RxTextView.textChanges(userNameField),
            RxTextView.textChanges(emailField),
            (login, email) -> login.length() > 0 && email.length() > 0
        ).subscribe(saveDataButton::setEnabled);
  }

  private void setOnClicks() {
    saveDataButton.setOnClickListener(view -> {
      databaseReference.child("username").setValue(userNameField.getText().toString());
      databaseReference.child("email").setValue(emailField.getText().toString());
    });
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null){
      getSupportActionBar().setTitle("Редактировать Профиль");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

}
