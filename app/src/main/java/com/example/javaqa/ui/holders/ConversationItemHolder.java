package com.example.javaqa.ui.holders;

import android.animation.Animator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.ui.adapters.ConversationAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class ConversationItemHolder extends RecyclerView.ViewHolder {

  private View itemView;
  private TextView title;
  private TextView time;
  private TextView views;
  private TextView comments;
  private ImageView userImage;
  private TextView rating;
  private TextView description;
  private TextView user_name;
  private ChipGroup hashtags;

  public ConversationItemHolder(@NonNull View itemView, final ConversationAdapter.OnItemClickListener listener) {
    super(itemView);

    this.itemView = itemView;
    this.title = itemView.findViewById(R.id.title_view);
    this.time = itemView.findViewById(R.id.publication_time);
    this.views = itemView.findViewById(R.id.count_of_views);
    this.description = itemView.findViewById(R.id.description_field);
    this.comments = itemView.findViewById(R.id.comments_count);
    this.rating = itemView.findViewById(R.id.rating_view);
    this.user_name = itemView.findViewById(R.id.user_name);
    this.hashtags = itemView.findViewById(R.id.hash_tags_chip_group);
    this.userImage = itemView.findViewById(R.id.conversation_user_image);

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

  public ImageView getUserImage() { return userImage; }

  public TextView getViews() {
    return views;
  }

  public TextView getComments() {
    return comments;
  }

  public TextView getRating() {
    return rating;
  }

  public TextView getDescription() { return description; }

  public TextView getUserName() {
    return user_name;
  }

  public void createChipGroup(String hashtag) {
    hashtags.removeAllViews();
    if(hashtag != null) {
      for (String c : hashtag.split(" ")) {
        Chip chip = new Chip(itemView.getContext());
        chip.setMinHeight(30);
        chip.setTextAppearance(android.R.style.TextAppearance_Material_Caption);
        chip.setChipBackgroundColor(itemView.getResources().getColorStateList(R.color.light_orange));
        chip.setTextColor(itemView.getResources().getColor(R.color.white));
        chip.setTextSize(10);
        chip.setCloseIconVisible(false);
        chip.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        chip.setText(c);
        hashtags.addView(chip, 0);
      }
    }
  }
}
