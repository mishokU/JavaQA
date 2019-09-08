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
import com.example.javaqa.adapters.ProfileCategoryAdapter;
import com.example.javaqa.items.ProfileCategoryItem;

import java.util.ArrayList;

public class ProfileStatisticFragment extends Fragment {

  private View view;
  private RecyclerView recyclerView;

  private RecyclerView.Adapter adapter;
  private RecyclerView.LayoutManager layoutManager;
  private ArrayList<ProfileCategoryItem> categoryItems;
  private SwipeRefreshLayout swipeRefreshLayout;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_profile_statistic,container,false);

    findViews();
    setUpAdapter();


    return view;
  }

  private void findViews() {
    recyclerView = view.findViewById(R.id.category_statistic_recycler_view);
  }

  private void setUpAdapter() {
    layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
    ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
    categoryItems = new ArrayList<>();
    adapter = new ProfileCategoryAdapter(categoryItems);

    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());
    categoryItems.add(new ProfileCategoryItem());

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);

    ((ProfileCategoryAdapter) adapter).setOnItemClickListener(position ->
        Toast.makeText(getContext(),"Clicked" + position,Toast.LENGTH_SHORT).show());
  }
}
