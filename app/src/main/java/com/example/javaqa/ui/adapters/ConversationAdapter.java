package com.example.javaqa.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.ui.holders.ConversationItemHolder;
import com.example.javaqa.models.PostData;

import java.util.ArrayList;
import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationItemHolder> {

  private List<PostData> posts = new ArrayList<>();
  private OnItemClickListener onItemClickListener;
  private View view;

  public interface OnItemClickListener{
    void onItemClick(int position);
  }

  public void setOnItemClickListener(OnItemClickListener listener){
    onItemClickListener = listener;
  }

  @NonNull
  @Override
  public ConversationItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation,parent,false);
    return new ConversationItemHolder(view, onItemClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull ConversationItemHolder holder, int position) {
    PostData conversationItem = posts.get(position);
    if(conversationItem != null) {

      holder.getTitle().setText(conversationItem.getTitle());
      holder.getComments().setText(String.valueOf(conversationItem.getComments()));
      holder.getTime().setText(conversationItem.getPublicationTime());
      holder.getViews().setText(String.valueOf(conversationItem.getCountOfViews()));
      holder.getRating().setText(String.valueOf(conversationItem.getRating()));
      holder.createChipGroup(conversationItem.getHashtags());
      holder.getUserName().setText(conversationItem.getUserName());

      //Picasso.get().load(conversationItem.getUserImageUrl()).placeholder(R.drawable.mytest).into(holder.getUserImage());
    }
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }

  public void setPosts(List<PostData> posts){
    this.posts = posts;
    notifyDataSetChanged();
  }

  public PostData getPost(int position){
    return posts.get(position);
  }

}
