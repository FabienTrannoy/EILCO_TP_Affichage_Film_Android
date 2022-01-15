package com.example.tp_affichage_films.ui.settings;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp_affichage_films.AffichageFilmApplication;
import com.example.tp_affichage_films.NavigationActivity;
import com.example.tp_affichage_films.databinding.FragmentSettingsBinding;

import java.util.Locale;


public class SettingsFragment extends Fragment {
    private SettingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;
    private Spinner spinner;
    private Button boutonSettings;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        AffichageFilmApplication app = (AffichageFilmApplication) root.getContext().getApplicationContext();

        boutonSettings = binding.boutonSettings;;
        spinner = binding.spinnerLanguage;
        String[] items = app.getLangues();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String langSelect = app.getLangues()[position];
                String[] langues = app.getLangues();
                String firstLang = langues[0];
                langues[0] = langSelect;
                langues[position] = firstLang;

                app.setLanguage(langSelect);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        boutonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale locale = new Locale(app.getLanguage().substring(3));
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                root.getContext().getResources().updateConfiguration(config,root.getContext().getResources().getDisplayMetrics());

                Intent intent = new Intent(root.getContext(), NavigationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        return root;
    }
}
