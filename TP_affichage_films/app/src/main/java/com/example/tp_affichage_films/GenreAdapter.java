package com.example.tp_affichage_films;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private final List<Genre> mGenres;

    public GenreAdapter(List<Genre> genres){
        mGenres = genres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View genreView = inflater.inflate(R.layout.row_genre, parent, false);

        return new GenreAdapter.ViewHolder(genreView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre genre = mGenres.get(position);

        TextView textView_genre = holder.textView_genre;
        String str = "- " + genre.getName();
        textView_genre.setText(str);
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_genre;

        public ViewHolder(View itemView){
            super(itemView);
            textView_genre = (TextView) itemView.findViewById(R.id.rowTextView);
        }
    }
}
