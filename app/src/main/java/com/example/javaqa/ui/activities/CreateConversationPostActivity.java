package com.example.javaqa.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.javaqa.R;
import com.example.javaqa.models.PostData;
import com.example.javaqa.models.UserMainData;
import com.example.javaqa.repository.remoteDatabase.firebase.FirebaseServerInstance;
import com.example.javaqa.viewmodels.PostsViewModel;
import com.example.javaqa.viewmodels.UserViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

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

  private PostsViewModel mPostsViewModel;
  private UserViewModel mUserViewModel;
  private Disposable publishButtonObserve;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_conversation_post);
    ButterKnife.bind(this);

    setUpToolbar();
    setViewModels();
    setUserData();
    setRxClicks();
    setOnClicks();
  }

  private void setViewModels() {
    mPostsViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
    mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
  }

  private void setUserData() {
    mUserViewModel.getUserMainData().observe(this, userMainData -> {
      userName.setText(userMainData.getUsername());
      Picasso.get().load(userMainData.getImageURL()).placeholder(R.drawable.mytest).into(profileImage);
    });
  }

  @Override
  protected void onStop() {
    super.onStop();
    mUserViewModel.getUserMainData().removeObservers(this);
    publishButtonObserve.dispose();
  }

  private void setRxClicks() {
    publishButtonObserve = Observable
        .combineLatest(
            RxTextView.textChanges(questionField),
            RxTextView.textChanges(descriptionField),
            RxTextView.textChanges(tagsField),
            (question, description, tags) -> question.toString().length() > 0 &&
                description.toString().length() > 0 && tags.toString().length() > 0
        ).subscribe(publishConversationButton::setEnabled);
  }

  private void setOnClicks() {
    addChip.setOnClickListener(view -> {
      if(!tagsField.getText().toString().isEmpty()) {
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
        chipGroup.addView(chip, 0);
      }
    });

    publishConversationButton.setOnClickListener(view -> sentDataToServer());
  }



  private String getCurrentDate(){
    Calendar calendar = Calendar.getInstance();
    return DateFormat.getDateInstance().format(calendar.getTime());
  }

  private String getHashTags(){
    StringBuilder hashtags = new StringBuilder();
    for(int i = 0; i < chipGroup.getChildCount(); i++){
      hashtags.append(((Chip) chipGroup.getChildAt(i)).getText()).append(" ");
    }
    return hashtags.toString();
  }

  private void sentDataToServer() {

    mPostsViewModel.insert(new PostData(
        questionField.getText().toString(),
        descriptionField.getText().toString(),
        getHashTags(),
        mUserViewModel.getUserUid(),
        mUserViewModel.getUserMainData().getValue().getUsername(),
        mUserViewModel.getUserMainData().getValue().getImageURL(),
        0,getCurrentDate(),0, 0,
        "default",
        mPostsViewModel.getKey()));

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

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null){
      getSupportActionBar().setTitle("Новый вопрос");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }
}
