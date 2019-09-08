package com.example.javaqa.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.adapters.ConversationAdapter;
import com.example.javaqa.adapters.FriendMainListAdapter;
import com.google.android.material.chip.ChipGroup;

import org.w3c.dom.Text;

public class ConversationItemHolder extends RecyclerView.ViewHolder {

  private TextView title;
  private TextView time;
  private TextView views;
  private TextView comments;
  private TextView rating;
  private ChipGroup hashtags;
  private TextView user_name;

  public ConversationItemHolder(@NonNull View itemView, final ConversationAdapter.OnItemClickListener listener) {
    super(itemView);

    this.title = itemView.findViewById(R.id.title_view);
    this.time = itemView.findViewById(R.id.publication_time);
    this.views = itemView.findViewById(R.id.count_of_views);
    this.comments = itemView.findViewById(R.id.comments_count);
    this.rating = itemView.findViewById(R.id.rating_view);
    this.hashtags = itemView.findViewById(R.id.hash_tags_chip_group);
    this.user_name = itemView.findViewById(R.id.user_name);

    itemView.setOnClickListener(view -> {
      if(listener != null) {
        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION) {
          listener.onItemClick(position);
        }
      }
    });
  }

  public TextView getTitle() {
    return title;
  }

  public TextView getTime() {
    return time;
  }

  public TextView getViews() {
    return views;
  }

  public TextView getComments() {
    return comments;
  }

  public TextView getRating() {
    return rating;
  }

  public ChipGroup getHashtags() {
    return hashtags;
  }

  public TextView getUser_name() {
    return user_name;
  }
}
