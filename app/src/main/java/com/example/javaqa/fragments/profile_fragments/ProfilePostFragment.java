package com.example.javaqa.fragments.profile_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.adapters.ConversationAdapter;
import com.example.javaqa.items.PostData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class ProfilePostFragment extends Fragment {

  private View view;
  private RecyclerView recyclerView;

  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private ArrayList<PostData> conversationItems;
  private SwipeRefreshLayout swipeRefreshLayout;

  private FirebaseDatabase firebaseDatabase;
  private DatabaseReference databaseReference;
  private FirebaseAuth firebaseAuth;
  private FirebaseUser firebaseUser;
  private FirebaseStorage firebaseStorage;
  private String userId;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_profile_posts,container,false);

    findViews();
    initFirebase();
    setUpAdapter();

    return view;
  }

  private void initFirebase() {
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    userId = firebaseUser.getUid();
    //Get to current user information in database;
    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Posts");
    firebaseStorage = FirebaseStorage.getInstance();

    loadUserPosts();
  }

  private void loadUserPosts() {
    databaseReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void findViews() {
    recyclerView = view.findViewById(R.id.profile_posts_recycler_view);
    swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
  }

  private void setUpAdapter() {
    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    conversationItems = new ArrayList<>();
    adapter = new ConversationAdapter(conversationItems);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    ((ConversationAdapter) adapter).setOnItemClickListener(position ->
        Toast.makeText(getContext(),"Clicked" + position,Toast.LENGTH_SHORT).show());
  }

}
