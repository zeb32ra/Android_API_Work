package com.example.pr15;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("manga")
    Call<ArrayList<Manga>> get_manga_list();

    @POST("manga/create")
    Call<Manga> postMangaList(@Body Manga manga);

    @DELETE("manga/delete/{id}")
    Call<String> deleteMangaList(@Path("id") int id);


}
