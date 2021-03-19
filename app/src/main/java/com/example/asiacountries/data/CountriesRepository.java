package com.example.asiacountries.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.asiacountries.model.Countries;
import com.example.asiacountries.util.CountriesRoomDatabase;

import java.util.List;

public class CountriesRepository {
    private CountriesDao countriesDao;

    private LiveData<List<Countries>> allCountries;

    public CountriesRepository(Application application) {
        CountriesRoomDatabase db = CountriesRoomDatabase.getDatabase(application);
        countriesDao = db.countriesDao();

        allCountries = countriesDao.getAllContacts();
    }
    public LiveData<List<Countries>> getAllCountries(){return allCountries;}
    public void insert(Countries countries){
        CountriesRoomDatabase.databaseWriteExecutor.execute(() ->{
            countriesDao.insert(countries);
        });
    }
}
