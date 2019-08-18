package com.example.javaqa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.holders.FriendItemHolder;
import com.example.javaqa.items.FriendItem;

import java.util.ArrayList;
import java.util.List;

public class FriendMainListAdapter extends RecyclerView.Adapter {

  private ArrayList<FriendItem> friends;
  private OnItemClickListener onItemClickListener;
  private View view;

  public FriendMainListAdapter(ArrayList<FriendItem> friendItemList) {
    this.friends = friendItemList;
  }

  public interface OnItemClickListener{
    void onItemClick(int position);
  }

  public void setOnItemClickListener(OnItemClickListener listener){
    onItemClickListener = listener;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item,parent,false);
    return new FriendItemHolder(view, onItemClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    FriendItem friendItem = friends.get(position);
    if(friendItem != null) {
      ((FriendItemHolder) holder).getUserName().setText(friendItem.getUserName());
    }
  }

  @Override
  public int getItemCount() {
    return friends.size();
  }
}
