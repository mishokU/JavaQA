package com.example.javaqa.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.ActivityUtils.GameStatusTypes;
import com.example.javaqa.R;
import com.example.javaqa.models.game.GameStatusModel;
import com.example.javaqa.ui.holders.GameStatusViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GameStatusAdapter extends RecyclerView.Adapter<GameStatusViewHolder> {

  private View view;
  private List<GameStatusModel> mGameStatusModelList = new ArrayList<>();
  private OnItemClickListener onItemClickListener;

  public interface OnItemClickListener{
    void onItemClick(int position);
  }

  public void setOnItemClickListener(OnItemClickListener listener){
    onItemClickListener = listener;
  }

  @NonNull
  @Override
  public GameStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_status,parent,false);
    return new GameStatusViewHolder(view, onItemClickListener);
  }

  @Override
  public void onBindViewHolder(@NonNull GameStatusViewHolder holder, int position) {
    GameStatusModel gameStatusModel = mGameStatusModelList.get(position);
    if(gameStatusModel != null){
      //holder.getUserImage()
      holder.getUserName().setText(gameStatusModel.getUsername());
      holder.getGameCategory().setText(gameStatusModel.getGameCategory());
      holder.getGameTime().setText(gameStatusModel.getGameTime());
    }
  }

  public void setGameStatuses(List<GameStatusModel> gameStatistics){
    mGameStatusModelList = gameStatistics;
    notifyDataSetChanged();
  }

  @Override
  public int getItemViewType(int position) {
    switch (mGameStatusModelList.get(position).getType()){
      case "your":
        return GameStatusTypes.YOUR_GAME;
      case "wait":
        return GameStatusTypes.WAITING_GAME;
      case "finished":
        return GameStatusTypes.FINISHED_GAME;
      default:
        return -1;
    }
  }

  @Override
  public int getItemCount() {
    return mGameStatusModelList.size();
  }

}
