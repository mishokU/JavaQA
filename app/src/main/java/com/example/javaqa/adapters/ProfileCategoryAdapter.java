package com.example.javaqa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.holders.ProfileCategoryItemHolder;
import com.example.javaqa.items.ProfileCategoryItem;

import java.util.ArrayList;

public class ProfileCategoryAdapter extends RecyclerView.Adapter {

  private ArrayList<ProfileCategoryItem> categoryItems;
  private OnItemClickListener onItemClickListener;
  private View view;

  public ProfileCategoryAdapter(ArrayList<ProfileCategoryItem> categoryItems) {
    this.categoryItems = categoryItems;
  }

  public interface OnItemClickListener{
    void onItemClick(int position);
  }

  public void setOnItemClickListener(ProfileCategoryAdapter.OnItemClickListener listener){
    onItemClickListener = listener;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_category,parent,false);
    return new ProfileCategoryItemHolder(view, onItemClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return categoryItems.size();
  }
}
