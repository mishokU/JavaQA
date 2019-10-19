package com.example.javaqa.ui.fragments.profile_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.ui.adapters.ConversationAdapter;
import com.example.javaqa.models.PostData;
import com.example.javaqa.viewmodels.PostsViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ProfileConversationFragment extends Fragment {

  private View view;
  private RecyclerView recyclerView;
  private TextView emptyList;

  private ConversationAdapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private ArrayList<PostData> posts;
  private SwipeRefreshLayout swipeRefreshLayout;
  private ProgressBar progressBar;
  private PostsViewModel mPostViewModel;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_profile_conversations,container,false);

    findViews();
    setUpAdapter();
    setViewModel();
    setUpSwipeRefresh();

    return view;
  }

  private void setViewModel() {
    mPostViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);
    mPostViewModel.getAllPosts().observe(this, postData -> adapter.setPosts(posts));
  }

  private void setUpSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  private void findViews() {
    recyclerView = view.findViewById(R.id.profile_conversations_recycler_view);
    swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
    progressBar = view.findViewById(R.id.progress_bar);
  }

  private void setUpAdapter() {
    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    posts = new ArrayList<>();
    adapter = new ConversationAdapter();

    //loadUserData();

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

  }

}
