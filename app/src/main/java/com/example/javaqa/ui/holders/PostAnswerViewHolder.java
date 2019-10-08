package com.example.javaqa.ui.holders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAnswerViewHolder extends RecyclerView.ViewHolder {

  private TextView mTitle;
  private TextView mAnswerTime;
  private TextView mUserName;
  private CircleImageView mUserImage;
  private TextView mRating;

  public PostAnswerViewHolder(@NonNull View itemView) {
    super(itemView);

    mTitle = itemView.findViewById(R.id.title_answer_item);
    mAnswerTime = itemView.findViewById(R.id.publication_time);
    mUserName = itemView.findViewById(R.id.user_name_answer_item);
    mUserImage = itemView.findViewById(R.id.user_image_answer_item);
    mRating = itemView.findViewById(R.id.like_count);
  }

  public TextView getTitle() {
    return mTitle;
  }

  public TextView getAnswerTime() {
    return mAnswerTime;
  }

  public TextView getUserName() {
    return mUserName;
  }

  public CircleImageView getUserImage() {
    return mUserImage;
  }

  public TextView getRating() {
    return mRating;
  }

}
