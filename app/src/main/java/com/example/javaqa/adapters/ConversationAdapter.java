package com.example.javaqa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.holders.ConversationItemHolder;
import com.example.javaqa.items.ConversationItem;
import java.util.ArrayList;


public class ConversationAdapter extends RecyclerView.Adapter {

  private ArrayList<ConversationItem> conversationItems;
  private OnItemClickListener onItemClickListener;
  private View view;

  public ConversationAdapter(ArrayList<ConversationItem> conversationItems) {
    this.conversationItems = conversationItems;
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
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation,parent,false);
    return new ConversationItemHolder(view, onItemClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    ConversationItem conversationItem = conversationItems.get(position);
    if(conversationItem != null) {
      /*((ConversationItemHolder) holder).getTitle().setText(conversationItem.getTitle());
      ((ConversationItemHolder) holder).getComments().setText(conversationItem.getComments());
      ((ConversationItemHolder) holder).getHashtags().setText(conversationItem.getHashtags());
      ((ConversationItemHolder) holder).getTime().setText(conversationItem.getPublication_time());
      ((ConversationItemHolder) holder).getViews().setText(conversationItem.getCount_of_views());
      ((ConversationItemHolder) holder).getRating().setText(conversationItem.getRating());*/
    }
  }

  @Override
  public int getItemCount() {
    return conversationItems.size();
  }
}
