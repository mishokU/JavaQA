package com.example.javaqa.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.ui.holders.FriendItemHolder;
import com.example.javaqa.models.FriendItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend,parent,false);
    return new FriendItemHolder(view, onItemClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    FriendItem friendItem = friends.get(position);
    if(friendItem != null) {
      ((FriendItemHolder) holder).getUserName().setText(friendItem.getUserName());
      Picasso.get().load(friendItem.getImageUrl()).placeholder(R.drawable.mytest).into(((FriendItemHolder) holder).getImageView());

      if(friendItem.isSwitcher()) {
        ((FriendItemHolder) holder).setImageView(view.getResources().getDrawable(R.drawable.question_mark_52px));
      }
    }
  }

  @Override
  public int getItemCount() {
    return friends.size();
  }
}
