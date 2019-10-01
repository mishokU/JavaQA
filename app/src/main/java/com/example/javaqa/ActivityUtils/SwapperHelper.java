package com.example.javaqa.ActivityUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.models.QueueItem;

import java.util.ArrayList;
import java.util.Collections;

public class SwapperHelper {

  private RecyclerView.Adapter adapter;
  public ArrayList<QueueItem> queueItems;
  private ItemTouchHelper helper;

  public SwapperHelper(RecyclerView.Adapter adapter, ArrayList<QueueItem> queueItems){
    this.queueItems = queueItems;
    this.adapter = adapter;
  }

  public void createHelper(){
    helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

        int position_dragged = viewHolder.getAdapterPosition();
        int position_targed = target.getAdapterPosition();

        Collections.swap(queueItems,position_dragged, position_targed);
        adapter.notifyItemMoved(position_dragged,position_targed);

        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

      }
    });
  }

  public ItemTouchHelper getHelper(){
    return helper;
  }
}
