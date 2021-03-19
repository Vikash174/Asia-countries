package com.example.asiacountries.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.asiacountries.model.Countries;

import java.util.List;

@Dao
public interface CountriesDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (Countries countries);


    @Query("DELETE FROM countries_detailed_table")
    void deleteAll();


    @Query("SELECT * FROM countries_detailed_table ORDER BY name ASC")
    LiveData<List<Countries>> getAllContacts();
}
