package com.example.javaqa.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.models.PostData;
import com.example.javaqa.ui.activities.CreateConversationPostActivity;
import com.example.javaqa.ui.adapters.PostAnswerAdapter;
import com.example.javaqa.viewmodels.CommentsViewModel;
import com.example.javaqa.viewmodels.PostsViewModel;
import com.example.javaqa.viewmodels.UserViewModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.like.LikeButton;
import com.like.OnLikeListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostItemFragment extends Fragment {


  private View mView;
  private PostAnswerAdapter mPostAnswerAdapter;
  private RecyclerView.LayoutManager mLayoutManager;

  private PostData postData;
  private PostsViewModel mPostsViewModel;
  private CommentsViewModel mCommentsViewModel;
  private UserViewModel mUserViewModel;
  private TextView mTitleField;
  private LikeButton mLikeButton;
  private RecyclerView mRecyclerView;
  private TextView emptyListText;
  private TextView likeCount;
  private TextView descriptionField;
  private ChipGroup chipGroup;
  private TextView userName;
  private ImageView userImage;
  private TextView publicationTime;
  private TextView countOfViews;
  private Spinner spinner;
  private TextView commentsCount;
  private SwipeRefreshLayout swipeRefreshLayout;
  private ProgressBar progressBar;
  private Toolbar mToolbar;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.activity_open_conversation_item, container, false);

    setRetainInstance(true);

    findViews();
    setUpAdapter();
    setCommentsViewModel();
    setUpLikes();
    setCurrentPostData();
    setUpSwipeRefresh();
    setSpinner();
    setToolbar();

    return mView;
  }

  private void setToolbar() {

  }

  private void setSpinner() {
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        Objects.requireNonNull(getContext()),
        R.array.category_comments,
        android.R.layout.simple_spinner_item);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
  }

  @Override
  public void onCreateOptionsMenu(@NotNull Menu menu, MenuInflater inflater) {
    inflater.inflate((R.menu.full_post_menu),menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  private void findViews() {

    mTitleField = mView.findViewById(R.id.title_full_item);
    mLikeButton = mView.findViewById(R.id.like_button);
    emptyListText = mView.findViewById(R.id.empty_list_text);
    likeCount = mView.findViewById(R.id.like_count);
    descriptionField = mView.findViewById(R.id.description_full_item);
    chipGroup = mView.findViewById(R.id.chip_group_for_full_item);
    userName = mView.findViewById(R.id.user_name_full_item);
    userImage = mView.findViewById(R.id.user_image_full_item);
    publicationTime = mView.findViewById(R.id.publication_time);
    countOfViews = mView.findViewById(R.id.count_of_views);
    mRecyclerView = mView.findViewById(R.id.comments_recycler_view);
    spinner = mView.findViewById(R.id.spinner);
    commentsCount = mView.findViewById(R.id.comments_count);
    swipeRefreshLayout = mView.findViewById(R.id.swipeRefreshLayout);
    progressBar = mView.findViewById(R.id.progress_bar);
  }

  private void setCommentsViewModel() {
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

  public CommentsViewModel getCommentsViewModel(){
    return mCommentsViewModel;
  }

  private void setUpLikes() {
    mLikeButton.setOnLikeListener(new OnLikeListener() {
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
    mLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) mLayoutManager).setStackFromEnd(true);
    mPostAnswerAdapter = new PostAnswerAdapter();
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mPostAnswerAdapter);
  }

  private void setCurrentPostData() {
    mTitleField.setText(postData.getTitle());
    descriptionField.setText(postData.getDescription());
    userName.setText(postData.getUserName());
    publicationTime.setText(postData.getPublicationTime());

    likeCount.setText(String.valueOf(postData.getRating()));
    countOfViews.setText(String.valueOf(postData.getCountOfViews()));
    commentsCount.setText(String.valueOf(postData.getComments()));
    //Picasso.get().load(postData.getUserImageUrl()).placeholder(R.drawable.mytest).into(userImage);
  }

  public void setItem(PostData post) {
    postData = post;
    if(postData !=  null) {
      postData.setCountOfViews(1);
    }
  }
}
