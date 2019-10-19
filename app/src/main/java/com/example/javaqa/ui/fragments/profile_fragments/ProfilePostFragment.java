package com.example.javaqa.ui.fragments.profile_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ProfilePostFragment extends Fragment {

  private View view;
  private RecyclerView recyclerView;

  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private ArrayList<PostData> conversationItems;
  private SwipeRefreshLayout swipeRefreshLayout;
  private PostsViewModel mPostViewModel;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_profile_posts,container,false);

    findViews();
    setPostViewModel();
    setUpAdapter();

    return view;
  }

  private void findViews() {
    recyclerView = view.findViewById(R.id.profile_posts_recycler_view);
    swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
  }

  private void setPostViewModel() {

  }


  private void setUpAdapter() {
    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    adapter = new ConversationAdapter();
    conversationItems = new ArrayList<>();
    //adapter = new ConversationAdapter(conversationItems);

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

  }

}
