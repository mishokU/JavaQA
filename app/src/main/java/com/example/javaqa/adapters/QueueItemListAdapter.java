package com.example.javaqa.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.holders.QueueItemHolder;
import com.example.javaqa.items.QueueItem;

import java.util.ArrayList;

public class QueueItemListAdapter extends RecyclerView.Adapter {

  private ArrayList<QueueItem> queueItems;
  private QueueItemListAdapter.OnItemClickListener onItemClickListener;
  private View view;

  public QueueItemListAdapter(ArrayList<QueueItem> queueItems) {
    this.queueItems = queueItems;
  }

  public interface OnItemClickListener{
    void onItemClick(int position);
  }

  public void setOnItemClickListener(QueueItemListAdapter.OnItemClickListener listener){
    onItemClickListener = listener;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_correct_queue,parent,false);
    return new QueueItemHolder(view, onItemClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    QueueItem queueItem = queueItems.get(position);
    if(queueItem != null) {
      ((QueueItemHolder)holder).getTextView().setText(queueItem.getStatement());
    }
  }

  @Override
  public int getItemCount() {
    return queueItems.size();
  }
}
