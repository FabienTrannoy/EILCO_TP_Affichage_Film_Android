package com.example.tp_affichage_films;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
    private final List<Film> mFilms;

    public FilmAdapter(List<Film> films) {
        this.mFilms = films;
    }

    @NonNull
    @Override
    public FilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View filmView = inflater.inflate(R.layout.item_filmposter, parent, false);

        return new ViewHolder(filmView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmAdapter.ViewHolder holder, int position) {
        Film film = mFilms.get(position);

        ImageView filmPoster = holder.filmPoster;
        Button boutonDetails = holder.boutonDetails;
        Button boutonFav = holder.boutonFav;
        Button boutonUnfav = holder.boutonUnfav;
        AffichageFilmApplication app = (AffichageFilmApplication) holder.itemView.getContext().getApplicationContext();

        if(app.isFavoris(film)){
            boutonFav.setVisibility(View.INVISIBLE);
            boutonUnfav.setVisibility(View.VISIBLE);
        } else {
            boutonUnfav.setVisibility(View.INVISIBLE);
            boutonFav.setVisibility(View.VISIBLE);
        }

        boutonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ajouter aux favoris
                app.addFavoris(film);
                boutonFav.setVisibility(View.INVISIBLE);
                boutonUnfav.setVisibility(View.VISIBLE);
            }
        });

        boutonUnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enlever des favoris
                app.supFavoris(film);
                boutonUnfav.setVisibility(View.INVISIBLE);
                boutonFav.setVisibility(View.VISIBLE);
            }
        });

        boutonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("id", film.getId());
                v.getContext().startActivity(intent);
            }
        });


        String load = "https://image.tmdb.org/t/p/w500" + film.getPoster_path();
        if(film.getPoster_path() == null){
            Glide
                    .with(filmPoster)
                    .load(holder.itemView.getContext().getDrawable(R.drawable.ic_banner_noimage_foreground))
                    .fitCenter()
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(75)))
                    .into(filmPoster);
        } else {
            Glide
                    .with(filmPoster)
                    .load(load)
                    .fitCenter()
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(75)))
                    .into(filmPoster);
        }



    }

    @Override
    public int getItemCount() {
        return mFilms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView filmPoster;
        public Button boutonDetails;
        public Button boutonFav;
        public Button boutonUnfav;

        public ViewHolder(View itemView){
            super(itemView);
            filmPoster = (ImageView) itemView.findViewById(R.id.imageView_filmposter);
            boutonDetails = (Button) itemView.findViewById(R.id.button_details);
            boutonFav = (Button) itemView.findViewById(R.id.button_fav);
            boutonUnfav = (Button) itemView.findViewById(R.id.button_unfav);
        }
    }
}
