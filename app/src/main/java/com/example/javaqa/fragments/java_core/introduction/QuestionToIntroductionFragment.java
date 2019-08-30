package com.example.javaqa.fragments.java_core.introduction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javaqa.R;

public class QuestionToIntroductionFragment extends Fragment {

  private View view;
  private Button checkButton;
  private RadioGroup radioGroup;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.javacore_second_introduction_fragment,container,true);

    findViews();
    checkButtonSetUp();
    checkCorrectAnswer();

    return view;
  }

  private void checkButtonSetUp() {
    checkButton.setOnClickListener(view -> {
      if(checkCorrectAnswer()){
        checkButton.setBackground(getResources().getDrawable(R.drawable.blue_button_correct_game_style));
        checkButton.setText("Продолжить");

      } else {
        checkButton.setBackground(getResources().getDrawable(R.drawable.blue_button_wrong_game_style));
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
