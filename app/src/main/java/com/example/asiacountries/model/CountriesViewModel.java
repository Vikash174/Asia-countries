package com.example.asiacountries.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.asiacountries.data.CountriesRepository;

import java.util.List;

public class CountriesViewModel extends AndroidViewModel {
    public static CountriesRepository countriesRepository;
    public final LiveData<List<Countries>> allCountries;
    public CountriesViewModel(@NonNull Application application) {
        super(application);
        countriesRepository = new CountriesRepository(application);
        allCountries = countriesRepository.getAllCountries();

    }

    public LiveData<List<Countries>> getAllCountries(){return allCountries;}
    public static void insert(Countries countries){countriesRepository.insert(countries);}
}
