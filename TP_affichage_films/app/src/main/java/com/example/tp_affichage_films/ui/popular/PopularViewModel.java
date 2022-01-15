package com.example.tp_affichage_films.ui.popular;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_affichage_films.FilmResponse;
import com.example.tp_affichage_films.FilmService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopularViewModel extends ViewModel {

    private MutableLiveData<FilmResponse> mFilmResponse;
    private String language;

    public PopularViewModel() {
        mFilmResponse = new MutableLiveData<>();

        FilmService filmSrv = new Retrofit.Builder()
                .baseUrl(FilmService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FilmService.class);

        filmSrv.popularMovies(this.language, "1").enqueue(new Callback<FilmResponse>() {
            @Override
            public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                mFilmResponse.setValue(response.body());
            }
            @Override
            public void onFailure(Call<FilmResponse> call, Throwable t) {}
        });

    }

    public LiveData<FilmResponse> getFilmResponse(){
        return mFilmResponse;
    }
    public void setLanguage(String l){
        this.language = l;
    }

}