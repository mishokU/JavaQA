package com.example.javaqa.fragments;

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
import com.example.javaqa.items.ConversationItem;

import java.util.ArrayList;

public class ProfilePostFragment extends Fragment {

  private View view;
  private RecyclerView recyclerView;

  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private ArrayList<ConversationItem> conversationItems;
  private SwipeRefreshLayout swipeRefreshLayout;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_profile_posts,container,false);

    findViews();
    setUpAdapter();

    return view;
  }

  private void findViews() {
    recyclerView = view.findViewById(R.id.profile_posts_recycler_view);
    swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
  }

  private void setUpAdapter() {
    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    conversationItems = new ArrayList<>();
    adapter = new ConversationAdapter(conversationItems);

    conversationItems.add(new ConversationItem());
    conversationItems.add(new ConversationItem());
    conversationItems.add(new ConversationItem());
    conversationItems.add(new ConversationItem());
    conversationItems.add(new ConversationItem());
    conversationItems.add(new ConversationItem());
    conversationItems.add(new ConversationItem());
    conversationItems.add(new ConversationItem());

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    ((ConversationAdapter) adapter).setOnItemClickListener(position ->
        Toast.makeText(getContext(),"Clicked" + position,Toast.LENGTH_SHORT).show());
  }

}
