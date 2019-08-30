package com.example.javaqa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.javaqa.ActivityUtils.ClickableEffectHelper;
import com.example.javaqa.ActivityUtils.LaunchActivityHelper;
import com.example.javaqa.R;
import com.example.javaqa.activities.java_core.IntroductionActivity;

import io.reactivex.rxjava3.core.Observable;

public class LearnFragment extends Fragment {

  private View mView;
  private LinearLayout mJavaCoreItemList;
  private LinearLayout mVCSItemList;
  private LinearLayout mBuildToolsItemList;
  private LinearLayout mDatabasesItemList;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_learn,container,false);

    findViews();
    setOnClicks();

    return mView;
  }

  private void setOnClicks() {

    for(int i = 0; i < mJavaCoreItemList.getChildCount(); i++){
      final int index = i;
      RelativeLayout relativeLayout = (RelativeLayout)mJavaCoreItemList.getChildAt(i);
      relativeLayout.setOnClickListener(view -> new LaunchActivityHelper().JavaCoreIndexingActivityHelper(getActivity(),index));
    }

    for(int i = 0; i < mVCSItemList.getChildCount(); i++){
      final int index = i;
      RelativeLayout relativeLayout = (RelativeLayout)mVCSItemList.getChildAt(i);
      relativeLayout.setOnClickListener(view -> new LaunchActivityHelper().VCSActivityHelper(getActivity(),index));
    }

    for(int i = 0; i < mBuildToolsItemList.getChildCount(); i++){
      final int index = i;
      RelativeLayout relativeLayout = (RelativeLayout)mBuildToolsItemList.getChildAt(i);
      relativeLayout.setOnClickListener(view -> new LaunchActivityHelper().BuildToolsActivityHelper(getActivity(),index));
    }

    for(int i = 0; i < mDatabasesItemList.getChildCount(); i++){
      final int index = i;
      RelativeLayout relativeLayout = (RelativeLayout)mDatabasesItemList.getChildAt(i);
      relativeLayout.setOnClickListener(view -> new LaunchActivityHelper().DataBasesActivityHelper(getActivity(),index));
    }

  }

  private void findViews() {
    mJavaCoreItemList = mView.findViewById(R.id.java_core_linear_layout);
    mVCSItemList = mView.findViewById(R.id.version_control_systems_linear_layout);
    mBuildToolsItemList = mView.findViewById(R.id.build_tools_linear_layout);
    mDatabasesItemList = mView.findViewById(R.id.data_base_linear_layout);
  }

}
