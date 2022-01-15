package com.example.tp_affichage_films.ui.popular;

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
import com.example.tp_affichage_films.FilmAdapter;
import com.example.tp_affichage_films.FilmResponse;
import com.example.tp_affichage_films.SpacesItemDecoration;
import com.example.tp_affichage_films.databinding.FragmentPopularBinding;

public class PopularFragment extends Fragment {

    private PopularViewModel popularViewModel;
    private FragmentPopularBinding binding;

    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        popularViewModel = new ViewModelProvider(this).get(PopularViewModel.class);

        binding = FragmentPopularBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AffichageFilmApplication app = (AffichageFilmApplication) root.getContext().getApplicationContext();
        popularViewModel.setLanguage(app.getLanguage());

        recyclerView = binding.rvPopularFilms;
        popularViewModel.getFilmResponse().observe(getViewLifecycleOwner(), new Observer<FilmResponse>() {
            @Override
            public void onChanged(FilmResponse filmResponse) {
                FilmAdapter adapter = new FilmAdapter(filmResponse.getItems());
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