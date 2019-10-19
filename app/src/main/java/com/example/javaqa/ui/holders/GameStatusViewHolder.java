package com.example.javaqa.ui.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.ui.adapters.GameStatusAdapter;
import com.google.android.material.chip.Chip;

import de.hdodenhof.circleimageview.CircleImageView;

public class GameStatusViewHolder extends RecyclerView.ViewHolder {

  private TextView userName;
  private TextView gameTime;
  private Chip gameCategory;
  private CircleImageView userImage;

  public GameStatusViewHolder(@NonNull View itemView, final GameStatusAdapter.OnItemClickListener listener) {
    super(itemView);

    userName = itemView.findViewById(R.id.game_status_user_name);
    userImage = itemView.findViewById(R.id.game_status_user_image);
    gameTime = itemView.findViewById(R.id.game_status_time);
    gameCategory = itemView.findViewById(R.id.game_status_chip);

    itemView.setOnClickListener(view -> {
      if(listener != null) {
        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION) {
          listener.onItemClick(position);
        }
      }
    });
  }

  public TextView getUserName() {
    return userName;
  }

  public TextView getGameTime() {
    return gameTime;
  }

  public Chip getGameCategory() {
    return gameCategory;
  }

  public CircleImageView getUserImage() {
    return userImage;
  }

}
