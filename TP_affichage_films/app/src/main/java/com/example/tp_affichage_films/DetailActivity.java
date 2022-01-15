package com.example.tp_affichage_films;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        AffichageFilmApplication app = (AffichageFilmApplication) getApplicationContext();

        // on récupère l'ID du film
        Intent intent = getIntent();
        setTitle("NOM_DU_FILM");//Par défaut
        String id_film = "566525";//Par défaut
        if (intent.hasExtra("id")) {
            id_film = intent.getStringExtra("id");
        }

        FilmService filmSrv = new Retrofit.Builder()
                .baseUrl(FilmService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FilmService.class);

        // Récupérer les détails d'un film par son ID
        filmSrv.movieDetails(id_film, app.getLanguage()).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                afficherFilm(response.body());
            }
            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                toastAfficheMessage(t.toString());
            }
        });

    }

    private void afficherFilm(Film film) {
        this.setTitle(film.getTitle());

        TextView description = (TextView) findViewById(R.id.textview_description);
        TextView releaseDate = (TextView) findViewById(R.id.textview_release_date);
        ImageView imageFilm = (ImageView) findViewById(R.id.imageview_film);

        description.setText(film.getOverview());
        releaseDate.setText(film.getRelease_date());

        // Affichage de l'image
        if(film.getBackdrop_path() == null){
            Glide
                    .with(imageFilm)
                    .load(getDrawable(R.drawable.ic_banner_noimage_foreground))
                    .into(imageFilm);
        } else {
            Glide
                    .with(imageFilm)
                    .load("https://image.tmdb.org/t/p/w500" + film.getBackdrop_path())
                    .into(imageFilm);
        }



        // affichage des genres
        RecyclerView rvGenres = (RecyclerView) findViewById(R.id.rvGenres);
        GenreAdapter genreAdapter = new GenreAdapter(Arrays.asList(film.getGenres().clone()));
        rvGenres.setAdapter(genreAdapter);
        rvGenres.setLayoutManager(new LinearLayoutManager(this));

    }

    private void toastAfficheMessage(String str){
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}