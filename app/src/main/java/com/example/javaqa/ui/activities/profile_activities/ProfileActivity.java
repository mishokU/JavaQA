package com.example.javaqa.ui.activities.profile_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.javaqa.ActivityUtils.LaunchActivityHelper;
import com.example.javaqa.R;
import com.example.javaqa.models.GameStatistics;
import com.example.javaqa.models.UserMainData;
import com.example.javaqa.ui.activities.StartActivity;
import com.example.javaqa.ui.activities.profile_activities.CreatePostActivity;
import com.example.javaqa.ui.activities.profile_activities.ProfileEditActivity;
import com.example.javaqa.ui.adapters.MainActivityViewPagerAdapter;
import com.example.javaqa.ui.fragments.profile_fragments.ProfileConversationFragment;
import com.example.javaqa.ui.fragments.profile_fragments.ProfilePostFragment;
import com.example.javaqa.ui.fragments.profile_fragments.ProfileStatisticFragment;
import com.example.javaqa.viewmodels.UserViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.profile_user_level) TextView userLevel;
  @BindView(R.id.profile_user_image) CircleImageView profileUserImage;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
  @BindView(R.id.experience_progress_bar) ProgressBar progressBar;

  private MainActivityViewPagerAdapter viewPagerAdapter;

  private ProgressBar progressBarWins;
  private ProgressBar progressBarLoses;
  private ProgressBar progressBarDraws;

  private ProgressBar javaCoreProgressBar;
  private ProgressBar buildToolsProgressBar;
  private ProgressBar vscProgressBar;
  private ProgressBar databasesProgressBar;

  private TextView winsPercents;
  private TextView losesPercents;
  private TextView drawsPercents;

  private TextView gamesCounts;
  private TextView gamesWithOutLosesCount;
  private TextView averageScore;

  private UserViewModel mUserViewModel;
  private UserMainData mUserMainData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    ButterKnife.bind(this);

    findViews();
    initUserViewModel();
    setUpToolbar();
  }

  private void initUserViewModel() {
    mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    mUserViewModel.getUserMainData().observe(this, this::updateFields);
    mUserViewModel.getGameStatistics().observe(this, this::fillData);
  }

  private void findViews() {
    progressBarWins = findViewById(R.id.win_progress_bar);
    progressBarLoses = findViewById(R.id.lose_progress_bar);
    progressBarDraws = findViewById(R.id.draw_progress_bar);

    winsPercents = findViewById(R.id.win_percent_from_progress_bar);
    losesPercents = findViewById(R.id.lose_percent_from_progress_bar);
    drawsPercents = findViewById(R.id.draw_percent_from_progress_bar);

    gamesCounts = findViewById(R.id.games_count);
    gamesWithOutLosesCount = findViewById(R.id.games_without_lose_count);
    averageScore = findViewById(R.id.average_counter_score);

    javaCoreProgressBar = findViewById(R.id.java_core_progress_bar);
    buildToolsProgressBar = findViewById(R.id.build_tools_progress_bar);
    vscProgressBar = findViewById(R.id.vsc_progress_bar);
    databasesProgressBar = findViewById(R.id.databases_progress_bar);
  }


  private void updateFields(UserMainData userMainData) {
    if(userMainData != null) {
      Objects.requireNonNull(getSupportActionBar()).setTitle(userMainData.getUsername());
      toolbar.setTitle(userMainData.getUsername());
      collapsingToolbarLayout.setTitle(userMainData.getUsername());
      userLevel.setText(userMainData.getLevel());
      Picasso.get().load(userMainData.getImageURL()).placeholder(R.drawable.mytest).into(profileUserImage);
    } else {
      mUserViewModel.setUserMainData(new UserMainData());
    }
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  private void fillData(GameStatistics gameStatistics) {
    if(gameStatistics != null) {
      try {
        progressBarWins.setProgress(gameStatistics.getWins() / gameStatistics.getGames() * 100);
        progressBarLoses.setProgress(gameStatistics.getLoses() / gameStatistics.getGames() * 100);
        progressBarDraws.setProgress(gameStatistics.getDraws() / gameStatistics.getGames() * 100);

        winsPercents.setText(String.valueOf(gameStatistics.getWins() / gameStatistics.getGames() * 100 + "%"));
        losesPercents.setText(String.valueOf(gameStatistics.getLoses() / gameStatistics.getGames() * 100 + "%"));
        drawsPercents.setText(String.valueOf(gameStatistics.getDraws() / gameStatistics.getGames() * 100 + "%"));

        gamesCounts.setText(String.valueOf(gameStatistics.getGames()));
        gamesWithOutLosesCount.setText(String.valueOf(gameStatistics.getGamesWithOutLoses()));
        averageScore.setText(String.valueOf(gameStatistics.getAverageScore()));

        javaCoreProgressBar.setProgress(gameStatistics.getJavaCoreFullGames() / gameStatistics.getJavaCoreWins());
        buildToolsProgressBar.setProgress(gameStatistics.getBuildToolsFullGames() / gameStatistics.getBuildToolsWins());
        vscProgressBar.setProgress(gameStatistics.getVscFullGames() / gameStatistics.getVscWins());
        databasesProgressBar.setProgress(gameStatistics.getDatabasesFullGames() / gameStatistics.getDatabasesWins());
      } catch (ArithmeticException ax) {
        ax.fillInStackTrace();
      }
    } else {
      mUserViewModel.setGameStatistic(new GameStatistics());
    }
  }

  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();
    return super.onSupportNavigateUp();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate((R.menu.profile_menu),menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
      case R.id.invite_friends:

        return true;
      case R.id.settings:

        return true;
      case R.id.share:

        return true;
      case R.id.edit_profile:
        launchActivity(ProfileEditActivity.class);
        return true;
      case R.id.exit_from_account:
        exitFromAccount();
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void launchActivity(Class activity) {
    Intent intent = new Intent(this, activity);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    startActivity(intent);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
    overridePendingTransition(0,0);
  }

  private void exitFromAccount(){
    FirebaseAuth.getInstance().signOut();
    Intent intent = new Intent(this, StartActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NO_ANIMATION);
    startActivity(intent);
    finish();
  }
}
