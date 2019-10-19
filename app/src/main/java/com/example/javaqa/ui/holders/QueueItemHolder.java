package com.example.javaqa.ui.holders;

import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.ui.adapters.QueueItemListAdapter;

public class QueueItemHolder extends RecyclerView.ViewHolder {

  private TextView textView;

  public TextView getTextView() {
    return textView;
  }

  public QueueItemHolder(@NonNull View itemView, final QueueItemListAdapter.OnItemClickListener listener) {
    super(itemView);

    textView = itemView.findViewById(R.id.text_for_queue);

    itemView.setOnClickListener(view -> {
      if(listener != null) {
        int position = getAdapterPosition();
        if(position != RecyclerView.NO_POSITION) {
          listener.onItemClick(position);
        }
      }
    });

  }
}
