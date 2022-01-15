package com.example.tp_affichage_films;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AffichageFilmApplication extends Application {
    private String language;
    private List<Film> favoris;
    private FilmService filmSrv;
    private String[] langues;

    @Override
    public void onCreate(){
        super.onCreate();
        this.language = "en-US";
        this.favoris = new ArrayList<Film>();
        this.filmSrv = new Retrofit.Builder()
                .baseUrl(FilmService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FilmService.class);
        this.langues = new String[]{"en-US", "fr-FR", "es-ES", "it-IT"};
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Film> getFavoris() {
        return favoris;
    }

    public void setFavoris(List<Film> favoris) {
        this.favoris = favoris;
    }

    public void addFavoris(Film film) {
        this.favoris.add(film);
    }

    public void supFavoris(Film film) {
        for(int i = 0; i < favoris.size(); i++){
            if(favoris.get(i).getId().equals(film.getId())){
                favoris.remove(i);
                break;
            }
        }
    }

    public boolean isFavoris(Film film){
        boolean bool = false;
        for(int i = 0; i < favoris.size(); i++){
            if(favoris.get(i).getId().equals(film.getId())){
                bool = true;
                break;
            }
        }
        return bool;
    }

    public FilmService getFilmSrv() {
        return filmSrv;
    }

    public void setFilmSrv(FilmService filmSrv) {
        this.filmSrv = filmSrv;
    }

    public String[] getLangues(){
        return langues;
    }

    public void setLangues(String[] langues){
        this.langues = langues;
    }
}
