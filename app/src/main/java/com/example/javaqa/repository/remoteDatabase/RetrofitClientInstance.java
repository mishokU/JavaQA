package com.example.javaqa.repository.remoteDatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

  private static Retrofit retrofit;
  private static final String URL = "https://javaqa-e226d.firebaseio.com/";

  public static RemotePostsService getRetrofitInstance() {
    if (retrofit == null) {
        retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
    return retrofit.create(RemotePostsService.class);
  }

}
