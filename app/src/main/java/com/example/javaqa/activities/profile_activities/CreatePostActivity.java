package com.example.javaqa.activities.profile_activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.javaqa.DataBase.Authentication.SQLite.DBHelper;
import com.example.javaqa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.javaqa.activities.ProfileActivity.DATA_NAME;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.post_text) EditText postText;
  @BindView(R.id.publish_post) Button publishPost;
  @BindView(R.id.insert_button) Button insert;
  @BindView(R.id.open_camera) ImageButton openCamera;
  @BindView(R.id.open_gallery) ImageButton openGallery;

  private DatabaseReference databasePostReference;
  private DatabaseReference databaseUserPostReference;
  private DatabaseReference firebaseDatabase;

  private FirebaseAuth firebaseAuth;
  private FirebaseUser firebaseUser;
  private FirebaseStorage firebaseStorage;
  private String userId;

  private SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_post);
    ButterKnife.bind(this);

    setUpToolbar();
    setUpFirebase();
    setOnClicks();
  }

  private void setUpFirebase() {
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    firebaseDatabase = FirebaseDatabase.getInstance().getReference();

    userId = firebaseUser.getUid();
    //Get to current user information in database;

    databasePostReference = firebaseDatabase.child("PostData");
    databaseUserPostReference = firebaseDatabase.child("Users").child(userId).child("PostData");

    firebaseStorage = FirebaseStorage.getInstance();
  }

  private void setOnClicks() {
    insert.setOnClickListener(this);
    openCamera.setOnClickListener(this);
    openGallery.setOnClickListener(this);
    publishPost.setOnClickListener(this);
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null) {
      getSupportActionBar().setTitle("Публикация");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public void onClick(View view) {
    if(view == insert) {
      //launchActivity(OpenAllPosts.class);
    } else if(view == openCamera) {
      //launchActivity(OpenCamera);
    } else if(view == openGallery) {
      //launchActivity(OpenGallery);
    } else if(view == publishPost) {
      createPost();
    }
  }

  private void createPost() {
    sharedPreferences = getPreferences(MODE_PRIVATE);
    String username = sharedPreferences.getString(DATA_NAME, "");

    Date d = new Date();
    CharSequence time = DateFormat.format("yyyy-MM-dd", d.getTime());

    HashMap<String, String> postData = new HashMap<>();
    postData.put("User Name", username);
    postData.put("Text", postText.getText().toString());
    postData.put("Views" , "0");
    postData.put("Rating", "0");
    postData.put("Comments", "0");
    postData.put("Publication_time", time.toString());

    String key = databaseUserPostReference.push().getKey();

    databaseUserPostReference.child(key).setValue(postData);

  }

  private void launchActivity(Class activity){
    Intent intent = new Intent(this, activity);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    startActivity(intent);
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
}
