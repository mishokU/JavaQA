package com.example.javaqa.ui.activities.new_game_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaqa.R;
import com.example.javaqa.models.GameStatistics;
import com.example.javaqa.models.game.GameStatusModel;
import com.example.javaqa.ui.activities.new_game_activities.ChoiceWeaponAlertDialog;
import com.example.javaqa.ui.activities.new_game_activities.StartNewGame;
import com.example.javaqa.ui.adapters.GameStatusAdapter;
import com.example.javaqa.viewmodels.GameStatusViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamesStatusActivity extends AppCompatActivity {

  @BindView(R.id.create_new_game) FloatingActionButton mNewGameButton;
  @BindView(R.id.your_turn_recycler_view) RecyclerView mYourGamesRecyclerView;
  @BindView(R.id.waiting_for_player_recycler_view) RecyclerView mWaitingGamesRecyclerView;
  @BindView(R.id.finished_games_recycler_view) RecyclerView mFinishedGamesRecyclerView;
  @BindView(R.id.toolbar) Toolbar toolbar;

  private GameStatusAdapter mYourGamesAdapter;
  private GameStatusAdapter mWaitingAdapter;
  private GameStatusAdapter mFinishedGamesAdapter;

  private RecyclerView.LayoutManager mLayoutManager;
  private RecyclerView.LayoutManager mWaitingManager;
  private RecyclerView.LayoutManager mFinishedManager;

  private GameStatusViewModel mGameStatusViewModel;
  private ChoiceWeaponAlertDialog choiceWeaponAlertDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.games_status_fragment);
    ButterKnife.bind(this);

    setToolbar();
    setUtils();
    setYourTurnRecyclerView();
    setWaitingRecyclerView();
    setFinishedRecyclerView();
    setGameStatusesViewModel();
    setOnNewGame();
  }

  private void setToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null){
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }

  private void setOnNewGame() {
    mNewGameButton.setOnClickListener(view -> choiceWeapon());
  }

  private void choiceWeapon() {
    choiceWeaponAlertDialog = new ChoiceWeaponAlertDialog(this);
    choiceWeaponAlertDialog.show(getSupportFragmentManager(), "");
  }

  public void fight(String weapon) {
    Toast.makeText(this,"weapon" + weapon, Toast.LENGTH_SHORT).show();
    launchActivity(weapon);
  }


  private void launchActivity(String weapon) {
    Toast.makeText(this,"weapon" + weapon, Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(this, StartNewGame.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    intent.putExtra("weapon", weapon);
    startActivity(intent);
  }

  private void setGameStatusesViewModel() {
    mGameStatusViewModel = ViewModelProviders.of(this).get(GameStatusViewModel.class);

    mGameStatusViewModel.getYourGames().observe(this,
        gameStatusModels -> mYourGamesAdapter.setGameStatuses(gameStatusModels));
    mGameStatusViewModel.getWaitingGames().observe(this,
        gameStatusModels -> mWaitingAdapter.setGameStatuses(gameStatusModels));
    mGameStatusViewModel.getFinishedGames().observe(this,
        gameStatusModels -> mFinishedGamesAdapter.setGameStatuses(gameStatusModels));
  }

  private void setUtils() {
    mLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) mLayoutManager).setStackFromEnd(true);

    mWaitingManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) mWaitingManager).setStackFromEnd(true);

    mFinishedManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
    ((LinearLayoutManager) mFinishedManager).setStackFromEnd(true);

    mYourGamesAdapter = new GameStatusAdapter();
    mWaitingAdapter = new GameStatusAdapter();
    mFinishedGamesAdapter = new GameStatusAdapter();
  }

  private void setFinishedRecyclerView() {
    mFinishedGamesRecyclerView.setHasFixedSize(true);
    mFinishedGamesRecyclerView.setLayoutManager(mLayoutManager);
    mFinishedGamesRecyclerView.setAdapter(mFinishedGamesAdapter);
    mFinishedGamesAdapter.setOnItemClickListener(position -> openCurrentGame(position, "finished"));
  }

  private void setWaitingRecyclerView() {
    mWaitingGamesRecyclerView.setHasFixedSize(true);
    mWaitingGamesRecyclerView.setLayoutManager(mWaitingManager);
    mWaitingGamesRecyclerView.setAdapter(mWaitingAdapter);
    mWaitingAdapter.setOnItemClickListener(position -> openCurrentGame(position, "waiting"));
  }

  private void setYourTurnRecyclerView() {
    mYourGamesRecyclerView.setHasFixedSize(true);
    mYourGamesRecyclerView.setLayoutManager(mFinishedManager);
    mYourGamesRecyclerView.setAdapter(mYourGamesAdapter);
    mYourGamesAdapter.setOnItemClickListener(position -> openCurrentGame(position, "your"));
  }

  private void openCurrentGame(int position, String waiting) {

  }
}
