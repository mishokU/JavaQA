package com.example.javaqa.ActivityUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.javaqa.R;

public class AlertDialogHelper extends DialogFragment {

  private String message;
  private Activity activity;

  public AlertDialogHelper(String message, Activity activity){
    this.message = message;
    this.activity = activity;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    builder.setTitle("Подсказка!")
        .setMessage(message)
        .setIcon(R.drawable.search_48px)
        .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            // Закрываем окно
            dialog.cancel();
          }
        });
    return builder.create();
  }
}
