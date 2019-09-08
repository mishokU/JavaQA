package com.example.javaqa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.ActivityUtils.LaunchActivityHelper;
import com.example.javaqa.R;
import com.example.javaqa.activities.NewGameActivity;
import com.google.android.material.button.MaterialButton;

public class GamesStatusFragment extends Fragment {

  private View view;
  private MaterialButton newGameButton;
  private RecyclerView yourGamesRecyclerView;
  private RecyclerView waitingGamesRecyclerView;
  private RecyclerView finishedGamesRecyclerView;
  private LaunchActivityHelper launchActivityHelper;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    view = inflater.inflate(R.layout.games_status_fragment, container, false);
    launchActivityHelper = new LaunchActivityHelper();

    findViews();
    setClicks();
    setUpFriendListAdapter();

    return view;
  }

  private void findViews() {
    newGameButton = view.findViewById(R.id.new_game_button);
    yourGamesRecyclerView = view.findViewById(R.id.your_turn_recycler_view);
    waitingGamesRecyclerView = view.findViewById(R.id.waiting_for_player_recycler_view);
    finishedGamesRecyclerView = view.findViewById(R.id.finished_games_recycler_view);
  }

  private void setClicks() {
    newGameButton.setOnClickListener(view -> launchActivityHelper.launchActivity(getActivity(), NewGameActivity.class, Intent.FLAG_ACTIVITY_NO_ANIMATION));
  }

  private void setUpFriendListAdapter() {

  }
}
