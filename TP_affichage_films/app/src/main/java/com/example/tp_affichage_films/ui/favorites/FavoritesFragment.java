package com.example.tp_affichage_films.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_affichage_films.AffichageFilmApplication;
import com.example.tp_affichage_films.Film;
import com.example.tp_affichage_films.FilmAdapter;
import com.example.tp_affichage_films.SpacesItemDecoration;
import com.example.tp_affichage_films.databinding.FragmentFavoritesBinding;

import java.util.List;

public class FavoritesFragment extends Fragment {
    private FavoritesViewModel favoritesViewModel;
    private FragmentFavoritesBinding binding;

    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AffichageFilmApplication app = (AffichageFilmApplication) root.getContext().getApplicationContext();
        favoritesViewModel.setFilmFavoris(app.getFavoris());

        recyclerView = binding.rvFavoritesFilms;
        favoritesViewModel.getFilmResponse().observe(getViewLifecycleOwner(), new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                FilmAdapter adapter = new FilmAdapter(films);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
                recyclerView.addItemDecoration(new SpacesItemDecoration(20));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
