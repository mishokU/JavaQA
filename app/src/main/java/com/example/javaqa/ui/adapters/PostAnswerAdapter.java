package com.example.javaqa.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.models.CommentsModel;
import com.example.javaqa.ui.holders.PostAnswerViewHolder;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PostAnswerAdapter extends RecyclerView.Adapter<PostAnswerViewHolder> {

  private List<CommentsModel> mComments = new ArrayList<>();
  private View mView;

  @NonNull
  @Override
  public PostAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation_answer,parent, false);
    return new PostAnswerViewHolder(mView);
  }

  @Override
  public void onBindViewHolder(@NonNull PostAnswerViewHolder holder, int position) {
    CommentsModel commentsModel = mComments.get(position);
    if(commentsModel != null) {
      holder.getTitle().setText(commentsModel.getAnswer());
      holder.getRating().setText(String.valueOf(commentsModel.getRating()));
      holder.getUserName().setText(commentsModel.getUserName());
      holder.getAnswerTime().setText(commentsModel.getTime());
      //Picasso.get().load();
    }
  }

  public void setComments(List<CommentsModel> comments){
    this.mComments = comments;
    Log.d(TAG, "setComments: " + comments.size());
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return mComments.size();
  }
}
