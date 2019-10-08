package com.example.javaqa.ActivityUtils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.javaqa.R;

public class AlertDialogHelper extends DialogFragment {

  private String mMessage;
  private Activity mActivity;

  public AlertDialogHelper(String message, Activity activity){
    this.mMessage = message;
    this.mActivity = activity;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
    builder.setTitle("Подсказка!")
        .setMessage(mMessage)
        .setIcon(R.drawable.search_48px)
        .setPositiveButton("ОК", (dialog, id) -> {
          // Закрываем окно
          dialog.cancel();
        });
    return builder.create();
  }
}
