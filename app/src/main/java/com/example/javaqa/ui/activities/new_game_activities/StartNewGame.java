package com.example.javaqa.ui.activities.new_game_activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.javaqa.R;
import com.example.javaqa.models.game.GameRoomModel;
import com.example.javaqa.viewmodels.GameViewModel;
import com.example.javaqa.viewmodels.UserViewModel;


import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StartNewGame extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.game_question_field) TextView questionField;
  @BindView(R.id.game_answer) EditText answerField;
  @BindView(R.id.time_progress_bar) ProgressBar timeProgressBar;

  //User data fields;
  @BindView(R.id.game_user_image) CircleImageView userImage;
  @BindView(R.id.game_user_score) TextView userScore;
  @BindView(R.id.game_user_name) TextView userName;
  @BindView(R.id.game_level_of_user) TextView userLevel;

  //Opponent data fields;
  @BindView(R.id.game_opponent_image) CircleImageView opponentImage;
  @BindView(R.id.game_opponent_name) TextView opponentName;
  @BindView(R.id.game_opponent_score) TextView opponentScore;
  @BindView(R.id.game_level_of_opponent) TextView opponentLevel;

  private GameViewModel mGameViewModel;
  private UserViewModel mUserViewModel;
  private String mUserReference;
  private String weapon;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_layout);
    ButterKnife.bind(this);

    setUpToolbar();
    getWeapon();
    setUpViewModels();
  }

  private void getWeapon(){
    Bundle bundle = getIntent().getExtras();
    if(bundle != null){
      weapon = bundle.getString("weapon");
    }
  }

  public void fight(String weapon) {
    this.weapon = weapon;
    Toast.makeText(this,"weapon" + weapon, Toast.LENGTH_SHORT).show();
  }

  private void setUpViewModels() {
    mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    mUserReference = mUserViewModel.getUserUid();

    mGameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
    mGameViewModel.setUserReference(mUserReference);
    mGameViewModel.setGameThemePath(weapon);
    mGameViewModel.getRoom().observe(this, new Observer<GameRoomModel>() {
      @Override
      public void onChanged(GameRoomModel gameRoomModel) {

      }
    });
  }

  //Firstly check if database have an active games otherwise create active game
  //If you find an active game, move this room to PlayingGames brunch


  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null) {
      getSupportActionBar().setTitle("");
      getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
  }


}
