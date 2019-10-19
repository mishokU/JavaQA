package com.example.javaqa.ui.activities.learning.base;

import androidx.appcompat.app.AppCompatActivity;

import com.example.javaqa.ui.fragments.Bases.BaseLearnFragment;

public abstract class BaseLearnActivity extends AppCompatActivity implements BaseLearnFragment.OnProgressListener {

  @Override
  public abstract void onProgressSend(String type, int progress);

}
