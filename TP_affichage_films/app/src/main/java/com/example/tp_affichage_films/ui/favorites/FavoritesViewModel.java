package com.example.tp_affichage_films.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_affichage_films.Film;

import java.util.ArrayList;
import java.util.List;


public class FavoritesViewModel extends ViewModel {
    private MutableLiveData<List<Film>> mFilmResponse;
    private List<Film> filmFavoris;

    public FavoritesViewModel() {
        filmFavoris = new ArrayList<Film>();
        mFilmResponse = new MutableLiveData<>();
        mFilmResponse.setValue(filmFavoris);
    }

    public LiveData<List<Film>> getFilmResponse(){
        return mFilmResponse;
    }
    public void setFilmFavoris(List<Film> f){
        this.filmFavoris = f;
        mFilmResponse.setValue(filmFavoris);
    }
}
