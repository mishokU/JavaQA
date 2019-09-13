package com.example.javaqa.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.items.PostData;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpenConversationItem extends AppCompatActivity {

  @BindView(R.id.increment_arrow) AppCompatImageButton incrementRatingArrow;
  @BindView(R.id.decrement_arrow) AppCompatImageButton decrementRatingArrow;
  @BindView(R.id.rating_full_item) TextView ratingField;
  @BindView(R.id.favourite_conversation_star) AppCompatImageButton favouritePostButton;
  @BindView(R.id.title_full_item) TextView titleField;
  @BindView(R.id.description_full_item) TextView descriptionField;
  @BindView(R.id.chip_group_for_full_item) ChipGroup chipGroup;
  @BindView(R.id.user_name_full_item) TextView userName;
  @BindView(R.id.user_image_full_item) ImageView userImage;
  @BindView(R.id.publication_time) TextView publicationTime;
  @BindView(R.id.count_of_views) TextView countOfViews;
  @BindView(R.id.comments_recycler_view) RecyclerView recyclerView;
  @BindView(R.id.spinner) Spinner spinner;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.comments_count) TextView commentsCount;
  @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;

  private PostData postData;
  private DatabaseReference databaseReference;
  private DatabaseReference currentPostReference;
  private FirebaseStorage firebaseStorage;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_open_conversation_item);
    ButterKnife.bind(this);

    setUpToolbar();
    setUpAdapter();
    catchDataFromBundle();
    updateData();
    setUpRatingArrows();
    setDataToFields();
    setUpSwipeRefresh();
  }

  private void setUpSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {

        swipeRefreshLayout.setRefreshing(false);
      }
    });
  }

  private void setUpRatingArrows() {
    incrementRatingArrow.setOnClickListener(view -> {
      int rating = Integer.parseInt(ratingField.getText().toString());
      ratingField.setText(String.valueOf(++rating));
      currentPostReference.child("rating").setValue(ratingField.getText().toString());
    });

    decrementRatingArrow.setOnClickListener(view -> {
      int rating = Integer.parseInt(ratingField.getText().toString());
      ratingField.setText(String.valueOf(--rating));
      currentPostReference.child("rating").setValue(ratingField.getText().toString());
    });

  }

  private void updateData() {
    databaseReference = FirebaseDatabase.getInstance().getReference();
    currentPostReference = databaseReference.child("PostData").child(postData.getKey());
    currentPostReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        PostData currentData = dataSnapshot.getValue(PostData.class);
        postData.setValues(currentData);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void setDataToFields() {
    ratingField.setText(postData.getRating());
    titleField.setText(postData.getTitle());
    descriptionField.setText(postData.getDescription());
    userName.setText(postData.getUserName());
    //Picasso.get().load(postData.getUserImageUrl()).placeholder(R.drawable.mytest).into(userImage);
    publicationTime.setText(postData.getPublicationTime());
    countOfViews.setText(postData.getCountOfViews());
    commentsCount.setText(postData.getComments());
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null) {
      getSupportActionBar().setTitle("Обсуждения");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  private void catchDataFromBundle() {
    Intent intent = this.getIntent();
    Bundle bundle = intent.getExtras();
    assert bundle != null;
    postData = (PostData) bundle.getSerializable("ItemData");
  }

  private void setUpAdapter() {

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
