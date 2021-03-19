package com.example.asiacountries.util;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.asiacountries.data.CountriesDao;
import com.example.asiacountries.model.Countries;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Countries.class},version = 1,exportSchema = false)
public abstract class CountriesRoomDatabase extends RoomDatabase {

    public abstract CountriesDao countriesDao();
    public static final int NUMBER_OF_THREADS = 5;

    private static volatile CountriesRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static CountriesRoomDatabase getDatabase (final Context context){
        if (INSTANCE == null){
            synchronized (CountriesRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CountriesRoomDatabase.class,"countries_database")
                            .addCallback(sRoomDataBaseCallback)
                            .build();

                }
            }
        }

        return INSTANCE;
    }
    private static final RoomDatabase.Callback sRoomDataBaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    databaseWriteExecutor.execute(()->{
                        CountriesDao countriesDao = INSTANCE.countriesDao();
                        countriesDao.deleteAll();

                        Countries countries = new Countries( "India",  "New delhi",  "flag",  "Asia", "south asia",  57356L,"China, Nepal, Bangladesh ","Hindi,english,punjabi");
                        countriesDao.insert(countries);

                    });

                }
            };

}
