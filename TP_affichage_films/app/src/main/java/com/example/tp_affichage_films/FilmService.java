package com.example.tp_affichage_films;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmService {
    public static final String ENDPOINT = "https://api.themoviedb.org";

    @GET("/3/movie/popular?api_key=98ad43961da857eaadc0ac15dba56991")
    Call<FilmResponse> popularMovies(@Query("language") String language, @Query("page") String page);

    @GET("/3/movie/upcoming?api_key=98ad43961da857eaadc0ac15dba56991")
    Call<FilmResponse> upcomingMovies(@Query("language") String language, @Query("page") String page);

    @GET("/3/movie/{id}?api_key=98ad43961da857eaadc0ac15dba56991")
    Call<Film> movieDetails(@Path("id") String id, @Query("language") String language);

    @GET("/3/search/movie?api_key=98ad43961da857eaadc0ac15dba56991")
    Call<FilmResponse> searchMovies(@Query("query") String query, @Query("language") String language, @Query("page") String page);
}
