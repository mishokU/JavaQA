package com.example.javaqa.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.javaqa.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.resources.TextAppearance;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

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
      chip.setTextSize(14);
      chip.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      chip.setText(tagsField.getText().toString());
      chip.setCheckable(true);
      chip.setOnCloseIconClickListener(view1 -> chipGroup.removeView(view1));
      tagsField.setText("");
      chipGroup.addView(chip,0);
    });
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

  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null){
      getSupportActionBar().setTitle("Новый вопрос");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }
}
