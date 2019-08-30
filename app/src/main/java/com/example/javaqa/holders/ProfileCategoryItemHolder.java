package com.example.javaqa.holders;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.adapters.ProfileCategoryAdapter;

public class ProfileCategoryItemHolder extends RecyclerView.ViewHolder {

  private TextView categoryName;
  private ProgressBar categoryProgress;

  public ProfileCategoryItemHolder(@NonNull View itemView, final ProfileCategoryAdapter.OnItemClickListener listener) {
    super(itemView);

    categoryName = itemView.findViewById(R.id.name_of_item);
    categoryProgress = itemView.findViewById(R.id.profile_category_progress_bar);

    itemView.setOnClickListener(view -> {
      if(listener != null) {
        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION) {
          listener.onItemClick(position);
        }
      }
    });
  }

  public TextView getCategoryName() {
    return categoryName;
  }

  public ProgressBar getCategoryProgress() {
    return categoryProgress;
  }

}
