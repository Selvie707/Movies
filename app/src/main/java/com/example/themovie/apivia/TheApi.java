package com.example.themovie.apivia;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TheApi {
    @GET("myapi.php")
    Call<Root> getTheMovie();

    @FormUrlEncoded
    @POST("post-movie.php")
    Call<Root> createMovie(@Field("title") String title,
                           @Field("poster_path") String posterpath,
                           @Field("genre") String genre,
                           @Field("vote_average") Double ratting,
                           @Field("release_date") String releasedate,
                           @Field("overview") String sinopsis);
}