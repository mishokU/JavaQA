package com.example.javaqa.ui.activities.new_game_activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.javaqa.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Random;

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

  //Firebase
  private DatabaseReference databaseReference;
  private DatabaseReference activeGamesTeference;
  private DatabaseReference singleGamesReference;
  private DatabaseReference gamesReference;
  private DatabaseReference userReference;
  private DatabaseReference opponentReference;
  private FirebaseStorage firebaseStorage;
  private FirebaseUser firebaseUser;
  private String userId;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_layout);
    ButterKnife.bind(this);

    setUpToolbar();
    getInformationAboutGames();
  }

  //Firstly check if database have an active games otherwise create active game
  //If you find an active game, move this room to PlayingGames brunch

  private void getInformationAboutGames() {
    databaseReference = FirebaseDatabase.getInstance().getReference();


    singleGamesReference = databaseReference.child("Games").child("SingleGames");
    gamesReference = databaseReference.child("Games");
    checkSingleGames();
    userId = firebaseUser.getUid();
    userReference = databaseReference.child("Users").child(userId).child("userData");

  }

  private void checkSingleGames() {
    singleGamesReference.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.hasChildren()) {
          //Get random index of elements
          Random random = new Random();
          int randomRoomIndex = random.nextInt((int)dataSnapshot.getChildrenCount());
          int index = 0;
          //Find useful index in children
          for(DataSnapshot ds : dataSnapshot.getChildren()) {
            if(index == randomRoomIndex) {

               String user_reference = ds.child("userRef").getValue().toString();
               String opponent_score = ds.child("userScore").getValue().toString();

               setDataFromExistingUser(user_reference, opponent_score);
               lockRoom(ds);
               break;
            }
            ++index;
          }
        } else {
          //Create room with one player
          userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              String myName = dataSnapshot.child("username").getValue().toString();
              String myImage = dataSnapshot.child("imageURL").getValue().toString();
              String myLevel = dataSnapshot.child("level").getValue().toString();

              HashMap<String, String> myData = new HashMap<>();
              myData.put("userRef", myName);
              myData.put("userScore", "0");

              String path = singleGamesReference.push().getKey();
              singleGamesReference.child(path).setValue(myData);

              userName.setText(myName);
              userLevel.setText(myLevel);
              Picasso.get().load(myImage).placeholder(R.drawable.mytest).into(userImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
          });
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void lockRoom(DataSnapshot ds) {
    
  }

  private void setDataFromExistingUser(String reference,String score) {
    opponentReference = databaseReference.child("Users").child(reference).child("userData");

    opponentReference.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        String opponent_name = dataSnapshot.child("username").getValue().toString();
        String opponent_image = dataSnapshot.child("imageURL").getValue().toString();
        String opponent_level = dataSnapshot.child("level").getValue().toString();

        opponentName.setText(opponent_name);
        opponentLevel.setText(opponent_level);
        opponentScore.setText(score);
        Picasso.get().load(opponent_image).placeholder(R.drawable.mytest).into(opponentImage);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  }

  private void setUpToolbar() {
    setSupportActionBar(toolbar);
    if(getSupportActionBar() != null) {
      getSupportActionBar().setTitle("");
      getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }
  }
}
