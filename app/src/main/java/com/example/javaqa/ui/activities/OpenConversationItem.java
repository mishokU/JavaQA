package com.example.javaqa.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.models.PostData;
import com.example.javaqa.models.UserMainData;
import com.example.javaqa.ui.adapters.PostAnswerAdapter;
import com.example.javaqa.ui.fragments.BottomSheetCommentsKeyBoard;
import com.example.javaqa.viewmodels.CommentsViewModel;
import com.example.javaqa.viewmodels.PostsViewModel;
import com.example.javaqa.viewmodels.UserViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.like.LikeButton;
import com.like.OnLikeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Binds;

public class OpenConversationItem extends AppCompatActivity {

  @BindView(R.id.title_full_item) TextView titleField;
  @BindView(R.id.like_button) LikeButton likeButton;
  @BindView(R.id.empty_list_text) TextView emptyListText;
  @BindView(R.id.like_count) TextView likeCount;
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
  @BindView(R.id.fab) FloatingActionButton openKeyBoardFab;
  @BindView(R.id.progress_bar) ProgressBar progressBar;

  private PostAnswerAdapter mPostAnswerAdapter;
  private RecyclerView.LayoutManager mLayoutManager;
  private BottomSheetCommentsKeyBoard bottomSheetCommentsKeyBoard;

  private PostData postData;
  private PostsViewModel mPostsViewModel;
  private CommentsViewModel mCommentsViewModel;
  private UserViewModel mUserViewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_open_conversation_item);
    ButterKnife.bind(this);

    setUpToolbar();
    catchDataFromBundle();
    setUpAdapter();
    setCommentsViewModel();
    addComment();
    setUpLikes();
    setCurrentPostData();
    setUpSwipeRefresh();
    setBottomSheet();
  }

  private void setBottomSheet() {
    bottomSheetCommentsKeyBoard = new BottomSheetCommentsKeyBoard();
    bottomSheetCommentsKeyBoard.setViewModel(mCommentsViewModel);
    bottomSheetCommentsKeyBoard.setUserViewModel(mUserViewModel);
  }

  private void addComment() {
    openKeyBoardFab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        bottomSheetCommentsKeyBoard.show(getSupportFragmentManager(), "input");
      }
    });
  }

  private void setCommentsViewModel() {
    mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

    mPostsViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
    mPostsViewModel.update(postData);

    mCommentsViewModel = ViewModelProviders.of(this).get(CommentsViewModel.class);
    mCommentsViewModel.setProgressBar(progressBar,emptyListText);
    mCommentsViewModel.setPathToComments(postData.getKey());

    mCommentsViewModel.getComments().observe(this,
        postAnswerModels -> mPostAnswerAdapter.setComments(postAnswerModels));
  }

  private void setUpSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(() -> swipeRefreshLayout.setRefreshing(false));
  }


  private void setUpLikes() {
    likeButton.setOnLikeListener(new OnLikeListener() {
      @Override
      public void liked(LikeButton likeButton) {
        postData.setRating(1);
        mPostsViewModel.update(postData);
      }

      @Override
      public void unLiked(LikeButton likeButton) {
        postData.setRating(-1);
        mPostsViewModel.update(postData);
      }
    });
  }

  private void setUpAdapter() {
    mLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) mLayoutManager).setStackFromEnd(true);
    mPostAnswerAdapter = new PostAnswerAdapter();
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setAdapter(mPostAnswerAdapter);
  }

  private void setCurrentPostData() {
    titleField.setText(postData.getTitle());
    descriptionField.setText(postData.getDescription());
    userName.setText(postData.getUserName());
    publicationTime.setText(postData.getPublicationTime());

    likeCount.setText(String.valueOf(postData.getRating()));
    countOfViews.setText(String.valueOf(postData.getCountOfViews()));
    commentsCount.setText(String.valueOf(postData.getComments()));
    //Picasso.get().load(postData.getUserImageUrl()).placeholder(R.drawable.mytest).into(userImage);
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

    if(postData !=  null) {
      postData.setCountOfViews(1);
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
}
