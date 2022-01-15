package com.example.tp_affichage_films.ui.upcoming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_affichage_films.AffichageFilmApplication;
import com.example.tp_affichage_films.FilmAdapter;
import com.example.tp_affichage_films.FilmResponse;
import com.example.tp_affichage_films.SpacesItemDecoration;
import com.example.tp_affichage_films.databinding.FragmentUpcomingBinding;

public class UpcomingFragment extends Fragment {

    private UpcomingViewModel upcomingViewModel;
    private FragmentUpcomingBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        upcomingViewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);

        binding = FragmentUpcomingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AffichageFilmApplication app = (AffichageFilmApplication) root.getContext().getApplicationContext();
        upcomingViewModel.setLanguage(app.getLanguage());

        recyclerView = binding.rvUpcomingFilms;
        upcomingViewModel.getFilmResponse().observe(getViewLifecycleOwner(), new Observer<FilmResponse>() {
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