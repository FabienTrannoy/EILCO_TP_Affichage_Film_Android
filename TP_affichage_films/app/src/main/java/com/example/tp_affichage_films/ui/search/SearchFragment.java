package com.example.tp_affichage_films.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_affichage_films.AffichageFilmApplication;
import com.example.tp_affichage_films.FilmAdapter;
import com.example.tp_affichage_films.FilmResponse;
import com.example.tp_affichage_films.R;
import com.example.tp_affichage_films.SpacesItemDecoration;
import com.example.tp_affichage_films.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;

    private RecyclerView recyclerView;
    private Button boutonSearch;
    private EditText editTextSearch;
    private TextView textViewMessageSearch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.rvSearch;
        boutonSearch = binding.ButtonSearch;
        editTextSearch = binding.EditTextSearch;
        textViewMessageSearch = binding.TextviewMessageSearch;

        AffichageFilmApplication app = (AffichageFilmApplication) root.getContext().getApplicationContext();
        searchViewModel.setLanguage(app.getLanguage());

        searchViewModel.getFilmResponse().observe(getViewLifecycleOwner(), new Observer<FilmResponse>() {
            @Override
            public void onChanged(FilmResponse filmResponse) {
                FilmAdapter adapter = new FilmAdapter(filmResponse.getItems());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
                recyclerView.addItemDecoration(new SpacesItemDecoration(20));
                int count = adapter.getItemCount();
                String str = String.valueOf(count) + " ";
                if(count > 1){
                    str += getString(R.string.label_movies_found);
                } else if(count == 1){
                    str += getString(R.string.label_movie_found);
                } else {
                    str = getString(R.string.label_no_movies_found);
                }

                textViewMessageSearch.setText(str);
            }
        });

        boutonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editTextSearch.getText().toString();
                if(!str.equals("")){
                    searchViewModel.searchFilm(str);
                }
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
