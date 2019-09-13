package com.example.javaqa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.javaqa.ActivityUtils.RevealAnimation;
import com.example.javaqa.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateConversationPostActivity extends AppCompatActivity {

  @BindView(R.id.publish_conversation) Button publishConversationButton;
  @BindView(R.id.teg_text) EditText tagsField;
  @BindView(R.id.conversation_user_name) TextView userName;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.question_field) EditText questionField;
  @BindView(R.id.description_field) EditText descriptionField;
  @BindView(R.id.conversation_user_image) CircleImageView profileImage;
  @BindView(R.id.conversation_chip_group) ChipGroup chipGroup;
  @BindView(R.id.add_chip) MaterialButton addChip;
  @BindView(R.id.root) LinearLayout linearLayout;

  private DatabaseReference databaseReference;
  private DatabaseReference userReference;
  private FirebaseAuth firebaseAuth;
  private FirebaseUser firebaseUser;
  private FirebaseStorage firebaseStorage;
  private String userId;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_conversation_post);
    ButterKnife.bind(this);

    setUpToolbar();
    initFirebase();
    setRxClicks();
    setOnClicks();
  }

  private void setRxClicks() {

  }

  private void setOnClicks() {
    addChip.setOnClickListener(view -> {
      Chip chip = new Chip(CreateConversationPostActivity.this);
      chip.setCloseIconEnabled(true);
      chip.setTextAppearance(android.R.style.TextAppearance_Material_Caption);
      chip.setChipBackgroundColor(getResources().getColorStateList(R.color.light_orange));
      chip.setTextColor(getResources().getColor(R.color.white));
      chip.setTextSize(12);
      chip.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      chip.setText(tagsField.getText().toString());
      chip.setOnCloseIconClickListener(view1 -> chipGroup.removeView(view1));
      tagsField.setText("");
      chipGroup.addView(chip,0);
    });

    publishConversationButton.setOnClickListener(view -> sentDataToServer());
  }

  private void sentDataToServer() {

    Calendar calendar = Calendar.getInstance();
    String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

    StringBuilder hashtags = new StringBuilder();
    for(int i = 0; i < chipGroup.getChildCount(); i++){
      hashtags.append(((Chip) chipGroup.getChildAt(i)).getText()).append(" ");
    }

    HashMap<String, String> item = new HashMap<>();
    item.put("userUrl", userReference.getKey());
    item.put("title", questionField.getText().toString());
    item.put("description", descriptionField.getText().toString());
    item.put("hashtags", hashtags.toString());
    item.put("countOfViews" , "0");
    item.put("rating", "0");
    item.put("comments", "0");
    item.put("publicationTime", currentDate);
    item.put("type","default");

    String unique_key = databaseReference.push().getKey();
    databaseReference.child(unique_key).setValue(item);
    finish();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
    overridePendingTransition(0,0);
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  private void initFirebase() {
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    userId = firebaseUser.getUid();
    //Get to current user information in database;
    databaseReference = FirebaseDatabase.getInstance().getReference().child("PostData");
    userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
    firebaseStorage = FirebaseStorage.getInstance();
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null){
      getSupportActionBar().setTitle("Новый вопрос");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }
}
