package com.example.javaqa.repository.remoteDatabase.firebase;

import androidx.lifecycle.MutableLiveData;

import com.example.javaqa.models.game.GameQuestionsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.disposables.Disposable;

public class QuestionThemesFirebase {

  private MutableLiveData<List<GameQuestionsModel>> questions;

  private DatabaseReference reference;
  private FirebaseServerInstance instance;
  private DatabaseReference themeReference;
  private DatabaseReference questionReference;

  private Disposable loadQuestions;

  public QuestionThemesFirebase(){
    instance = FirebaseServerInstance.getInstance();
    reference = instance.getReference().child("QuestionThemes");
    questions = new MutableLiveData<>();
  }

  public void setQuestionThemeReference(String theme){
    themeReference = reference.child(theme);
    questionReference = themeReference.child("Questions");
    getRandomQuestions();
  }

  private void getRandomQuestions(){
    Disposable loadMax = RxFirebaseDatabase.observeSingleValueEvent
        (questionReference, DataSnapshot::getChildrenCount)
        .subscribe(aLong -> {
          Long vector = ThreadLocalRandom.current().nextLong(aLong - 5);
          loadQuestions = RxFirebaseDatabase.observeSingleValueEvent(
              questionReference.startAt(vector).endAt(vector + 5), DataSnapshotMapper.listOf(GameQuestionsModel.class))
              .subscribe( gameQuestionsModels -> questions.postValue(gameQuestionsModels));
          loadQuestions.dispose();
        });
    loadMax.dispose();
  }

  public MutableLiveData<List<GameQuestionsModel>> getQuestions(){
    return questions;
  }
}
