package com.example.javaqa.ui.activities.new_game_activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.javaqa.ui.activities.NewGameActivity;

import java.util.Objects;

public class ChoiceWeaponAlertDialog extends DialogFragment {

  private String weapon;
  private Activity activity;

  public ChoiceWeaponAlertDialog( Activity activity){
    this.activity = activity;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    String[] weapons = {"Java Core", "Build Tools", "Databases", "VCS"};
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    builder.setTitle("Choice weapon!")
        .setSingleChoiceItems(weapons, 0, (dialogInterface, i) -> weapon = weapons[i])
        .setPositiveButton("Fight", (dialogInterface, i) -> ((NewGameActivity) Objects.requireNonNull(getActivity())).fight(weapon))
        .setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());
    return builder.create();
  }

}
