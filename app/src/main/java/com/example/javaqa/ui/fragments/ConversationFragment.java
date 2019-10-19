package com.example.javaqa.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.*;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.models.PostData;
import com.example.javaqa.ui.activities.OpenConversationItem;
import com.example.javaqa.ui.adapters.ConversationAdapter;
import com.example.javaqa.viewmodels.PostsViewModel;
import com.example.javaqa.viewmodels.UserViewModel;

import java.util.Objects;

public class ConversationFragment extends Fragment {

  private View mView;
  private PostItemListener postItemListener;
  private RecyclerView mRecyclerView;
  private ProgressBar mProgressBar;
  private SwipeRefreshLayout mSwipeRefreshLayout;

  private ConversationAdapter mAdapter;
  private RecyclerView.LayoutManager mLayoutManager;

  private PostsViewModel mPostsViewModel;
  private UserViewModel mUserViewModel;

  public interface PostItemListener{
    void onPostInput(PostData postData);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPostsViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_conversation,container,false);
    setRetainInstance(true);

    findAllViews();
    setUpAdapter();
    observePosts();
    setUpSpinner();
    setUpSwipeRefreshLayout();

    return mView;
  }

  private void observePosts() {
    mPostsViewModel.getAllPosts().observe(this, postData -> mAdapter.setPosts(postData));
  }

  private void findAllViews() {
    mRecyclerView = mView.findViewById(R.id.public_conversation_place);
    mSwipeRefreshLayout = mView.findViewById(R.id.swipeRefreshLayout);
    mProgressBar = mView.findViewById(R.id.progress_bar);
  }

  private void setUpSpinner(){
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
        Objects.requireNonNull(getContext()),
        R.array.category,
        android.R.layout.simple_spinner_item);

    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //mSpinner.setAdapter(adapter);
  }

  private void setUpAdapter() {
    mLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) mLayoutManager).setStackFromEnd(true);
    mAdapter = new ConversationAdapter();
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);
    mAdapter.setOnItemClickListener(this::openConversationItem);
  }

  private void openConversationItem(int position) {
    postItemListener.onPostInput(mAdapter.getPost(position));

    /*Intent intent = new Intent(getActivity(), OpenConversationItem.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("ItemData", mAdapter.getPost(position));
    intent.putExtras(bundle);
    startActivity(intent);*/
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    if(context instanceof PostItemListener){
      postItemListener = (PostItemListener) context;
    } else {
      throw new RuntimeException(context.toString()
      + "must implement item post listener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    postItemListener = null;
  }

  private void setUpSwipeRefreshLayout(){
    mSwipeRefreshLayout.setOnRefreshListener(() -> {
      mSwipeRefreshLayout.setRefreshing(false);
    });
  }

}
