package com.example.javaqa.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.javaqa.ActivityUtils.GameStatusTypes;
import com.example.javaqa.R;
import com.example.javaqa.ui.activities.new_game_activities.GamesStatusActivity;
import com.example.javaqa.ui.activities.learning.LearnActivity;
import com.example.javaqa.ui.activities.profile_activities.ProfileActivity;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainBottomSheet extends BottomSheetDialogFragment {

  private View mView;
  private NavigationView mNavigationView;
  private RelativeLayout circularRevealRelativeLayout;
  private AppCompatImageView mCloseDialog;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.main_bottom_sheet_fragment, container, false);
    return mView;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    findViews();
    setClicks();
  }

  /*@NotNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    BottomSheetDialog dialog =  (BottomSheetDialog)super.onCreateDialog(savedInstanceState);
    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
      @Override
      public void onShow(DialogInterface dialogInterface) {
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(mNavigationView);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
          @Override
          public void onStateChanged(@NonNull View view, int i) {

          }

          @Override
          public void onSlide(@NonNull View view, float v) {

          }
        });
      }
    });
    return dialog;
  }*/

  private void setClicks() {
    circularRevealRelativeLayout.setOnClickListener(view -> launchActivity(ProfileActivity.class));
    mNavigationView.setNavigationItemSelectedListener(menuItem -> {
      switch (menuItem.getItemId()){
        case R.id.learning_button:
          launchActivity(LearnActivity.class);
          return true;
        case R.id.friends_button:
          //launchActivity();
          return true;
        case R.id.archive_button:
          //launchActivity();
          return true;
        case R.id.chats_button:
          //launchActivity();
          return true;
        case R.id.play_game:
          launchActivity(GamesStatusActivity.class);
          return true;
      }
      return false;
    });
    mCloseDialog.setOnClickListener(view -> dismiss());

  }

  private void launchActivity(Class profileActivityClass) {
    Intent intent = new Intent(getActivity(),profileActivityClass);
    startActivity(intent);
    dismiss();
  }

  private void findViews() {
    circularRevealRelativeLayout = mView.findViewById(R.id.circle_relative);
    mNavigationView = mView.findViewById(R.id.navigation_view);
    mCloseDialog = mView.findViewById(R.id.close_image_view);
  }

}
