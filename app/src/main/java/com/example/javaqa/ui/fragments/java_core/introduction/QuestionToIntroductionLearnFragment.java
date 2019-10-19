package com.example.javaqa.ui.fragments.java_core.introduction;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.javaqa.R;
import com.example.javaqa.ui.fragments.Bases.BaseLearnFragment;
import com.google.android.material.button.MaterialButton;

public class QuestionToIntroductionLearnFragment extends BaseLearnFragment {

  private View view;
  private MaterialButton checkButton;
  private RadioGroup radioGroup;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.javacore_second_introduction_fragment,container,false);

    findViews();
    checkButtonSetUp();
    checkCorrectAnswer();

    return view;
  }

  private void checkButtonSetUp() {
    checkButton.setOnClickListener(view -> {
      if(checkCorrectAnswer()){
        checkButton.setText("Продолжить");
        checkButton.setRippleColor(ColorStateList.valueOf(getResources().getColor(R.color.button_color)));
        progressListener.onProgressSend("JavaCore",1);
      } else {
        checkButton.setRippleColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
      }
    });
  }

  private Boolean checkCorrectAnswer() {
    int id = radioGroup.getCheckedRadioButtonId();
    return id == R.id.correct_answer;
  }

  private void findViews() {
    checkButton = view.findViewById(R.id.check_button);
    radioGroup = view.findViewById(R.id.radio_group);
  }

}
