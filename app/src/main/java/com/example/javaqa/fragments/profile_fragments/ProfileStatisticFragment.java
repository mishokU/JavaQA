package com.example.javaqa.fragments.profile_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.javaqa.R;
import com.example.javaqa.items.GameStatistics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;

public class ProfileStatisticFragment extends Fragment {

  private View view;
  private RecyclerView recyclerView;
  private SwipeRefreshLayout swipeRefreshLayout;

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

  private DatabaseReference userStatisticReference;
  private DatabaseReference userReference;
  private DatabaseReference databaseReference;
  private FirebaseAuth firebaseAuth;
  private FirebaseUser firebaseUser;
  private FirebaseStorage firebaseStorage;
  private String userId;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_profile_statistic,container,false);

    findViews();
    setUpFirebase();
    setUpSwipeRefresh();

    return view;
  }

  private void findViews() {
    swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

    progressBarWins = view.findViewById(R.id.win_progress_bar);
    progressBarLoses = view.findViewById(R.id.lose_progress_bar);
    progressBarDraws = view.findViewById(R.id.draw_progress_bar);

    winsPercents = view.findViewById(R.id.win_percent_from_progress_bar);
    losesPercents = view.findViewById(R.id.lose_percent_from_progress_bar);
    drawsPercents = view.findViewById(R.id.draw_percent_from_progress_bar);

    gamesCounts = view.findViewById(R.id.games_count);
    gamesWithOutLosesCount = view.findViewById(R.id.games_without_lose_count);
    averageScore = view.findViewById(R.id.average_counter_score);

    javaCoreProgressBar = view.findViewById(R.id.java_core_progress_bar);
    buildToolsProgressBar = view.findViewById(R.id.build_tools_progress_bar);
    vscProgressBar = view.findViewById(R.id.vsc_progress_bar);
    databasesProgressBar = view.findViewById(R.id.databases_progress_bar);
  }

  private void setUpFirebase() {
    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    userId = firebaseUser.getUid();
    databaseReference = FirebaseDatabase.getInstance().getReference();
    //Get to current user information in database;
    userReference = databaseReference.child("Users").child(userId);
    userStatisticReference = databaseReference.child("Users").child(userId).child("GameStatistics");
    firebaseStorage = FirebaseStorage.getInstance();

    createStatisticsBunch();
  }

  private void createStatisticsBunch() {
    userReference.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(!dataSnapshot.hasChild("GameStatistics")) {
          createStatisticData();
        } else {
          loadUserStatistic();
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void createStatisticData() {
    HashMap<String, Integer> statisticMap = new HashMap<>();

    statisticMap.put("wins", 0);
    statisticMap.put("loses", 0);
    statisticMap.put("draws", 0);

    statisticMap.put("games", 0);
    statisticMap.put("averageScore", 0);
    statisticMap.put("gamesWithOutLoses", 0);

    statisticMap.put("javaCoreFullGames", 0);
    statisticMap.put("buildToolsFullGames", 0);
    statisticMap.put("vscFullGames", 0);
    statisticMap.put("databasesFullGames", 0);

    statisticMap.put("javaCoreWins", 0);
    statisticMap.put("buildToolsWins", 0);
    statisticMap.put("vscWins", 0);
    statisticMap.put("databasesWins", 0);

    userStatisticReference.setValue(statisticMap);
  }

  private void loadUserStatistic() {
    userStatisticReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        GameStatistics gameStatistics = dataSnapshot.getValue(GameStatistics.class);
        assert gameStatistics != null;
        fillData(gameStatistics);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void fillData(GameStatistics gameStatistics) {
    try {
      progressBarWins.setProgress (gameStatistics.getWins()  / gameStatistics.getGames() * 100);
      progressBarLoses.setProgress(gameStatistics.getLoses() / gameStatistics.getGames() * 100);
      progressBarDraws.setProgress(gameStatistics.getDraws() / gameStatistics.getGames() * 100);

      winsPercents.setText(String.valueOf( gameStatistics.getWins()  / gameStatistics.getGames() * 100  + "%"));
      losesPercents.setText(String.valueOf(gameStatistics.getLoses() / gameStatistics.getGames() * 100  + "%"));
      drawsPercents.setText(String.valueOf(gameStatistics.getDraws() / gameStatistics.getGames() * 100  + "%"));

      gamesCounts.setText(String.valueOf(gameStatistics.getGames()));
      gamesWithOutLosesCount.setText(String.valueOf(gameStatistics.getGamesWithOutLoses()));
      averageScore.setText(String.valueOf(gameStatistics.getAverageScore()));

      javaCoreProgressBar.setProgress(gameStatistics.getJavaCoreFullGames() / gameStatistics.getJavaCoreWins());
      buildToolsProgressBar.setProgress(gameStatistics.getBuildToolsFullGames() / gameStatistics.getBuildToolsWins());
      vscProgressBar.setProgress(gameStatistics.getVscFullGames() / gameStatistics.getVscWins());
      databasesProgressBar.setProgress(gameStatistics.getDatabasesFullGames() / gameStatistics.getDatabasesWins());
    }catch(ArithmeticException ax) {
      ax.fillInStackTrace();
    }
  }

  private void setUpSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(() -> {
      loadUserStatistic();
      swipeRefreshLayout.setRefreshing(false);
    });
  }
}
