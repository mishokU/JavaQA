package com.example.javaqa.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.adapters.ConversationAdapter;
import com.example.javaqa.items.ConversationItem;

import java.util.ArrayList;

public class ConversationFragment extends Fragment {

  private View view;
  private RecyclerView recyclerView;
  private SearchView searchView;

  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private ArrayList<ConversationItem> conversationItems;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.conversation_fragment,container,false);

    findAllViews();
    setUpAdapter();

    return view;
  }

  private void findAllViews() {
    recyclerView = view.findViewById(R.id.public_conversation_place);
    searchView = view.findViewById(R.id.search_conversation);
  }

  private void setUpAdapter() {
    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    conversationItems = new ArrayList<>();
    adapter = new ConversationAdapter(conversationItems);

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
