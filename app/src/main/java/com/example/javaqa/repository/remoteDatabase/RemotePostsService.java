package com.example.javaqa.repository.remoteDatabase;

import androidx.room.Query;

import com.example.javaqa.models.PostData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RemotePostsService {

  @GET("/PostData.json")
  Call<List<PostData>> getAllPosts();
}
