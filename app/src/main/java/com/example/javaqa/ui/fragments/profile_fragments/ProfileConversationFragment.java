package com.example.javaqa.ui.fragments.profile_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.ui.adapters.ConversationAdapter;
import com.example.javaqa.models.PostData;
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

public class ProfileConversationFragment extends Fragment {

  private View view;
  private RecyclerView recyclerView;
  private TextView emptyList;

  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private ArrayList<PostData> posts;
  private SwipeRefreshLayout swipeRefreshLayout;

  private DatabaseReference databaseReference;
  private DatabaseReference allPostsReference;
  private FirebaseUser firebaseUser;
  private Query userNameQuery;
  private FirebaseStorage firebaseStorage;
  private String userId;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_profile_posts,container,false);

    findViews();
    initFirebase();
    setUpAdapter();
    setUpSwipeRefresh();

    return view;
  }

  private void setUpSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      posts.clear();
      loadCurrentUserConversations();
      swipeRefreshLayout.setRefreshing(false);
    });
  }

  private void findViews() {
    recyclerView = view.findViewById(R.id.profile_posts_recycler_view);
    swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
  }

  private void setUpAdapter() {
    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    posts = new ArrayList<>();
    //adapter = new ConversationAdapter(posts);

    //loadUserData();

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    ((ConversationAdapter) adapter).setOnItemClickListener(position ->
        Toast.makeText(getContext(),"Clicked" + position,Toast.LENGTH_SHORT).show());
  }

  private void initFirebase() {
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    assert firebaseUser != null;
    userId = firebaseUser.getUid();
    databaseReference = FirebaseDatabase.getInstance().getReference();
    allPostsReference = databaseReference.child("PostData");
    //Get to current user information in database;
    firebaseStorage = FirebaseStorage.getInstance();

    loadCurrentUserConversations();
  }

  private void loadCurrentUserConversations() {
    allPostsReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for(DataSnapshot post: dataSnapshot.getChildren()) {
          if(post.child("userUrl").getValue().equals(userId)) {
            PostData postData = post.getValue(PostData.class);
            posts.add(postData);
            adapter.notifyDataSetChanged();
          }
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

}
