package com.example.javaqa.ActivityUtils;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

public class PageSwapperLoader implements LoaderManager.LoaderCallbacks<FragmentTransaction> {


  @NonNull
  @Override
  public Loader<FragmentTransaction> onCreateLoader(int id, @Nullable Bundle args) {
    return null;
  }

  @Override
  public void onLoadFinished(@NonNull Loader<FragmentTransaction> loader, FragmentTransaction data) {

  }

  @Override
  public void onLoaderReset(@NonNull Loader<FragmentTransaction> loader) {

  }
}
