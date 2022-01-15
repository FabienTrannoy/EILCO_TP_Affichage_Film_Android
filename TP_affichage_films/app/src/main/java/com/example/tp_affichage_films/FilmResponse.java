package com.example.tp_affichage_films;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmResponse {
    @SerializedName("results")
    private List<Film> items;
    public List<Film> getItems(){
        return items;
    }
}
