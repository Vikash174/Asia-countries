package com.example.asiacountries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.asiacountries.databinding.ActivityMainBinding;
import com.example.asiacountries.model.Countries;
import com.example.asiacountries.model.CountriesViewModel;
import com.example.asiacountries.util.Constants;
import com.example.asiacountries.util.CountriesRoomDatabase;
import com.example.asiacountries.util.RecyclerViewAdapter;
import com.example.asiacountries.util.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private CountriesViewModel countriesViewModel;

    private RequestQueue queue;
    private ActivityMainBinding binding;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Countries> countriesArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        queue = Singleton.getInstance(this).getRequestQueue();
        countriesArrayList = new ArrayList<>();



        countriesViewModel = new ViewModelProvider
                .AndroidViewModelFactory(MainActivity.this.getApplication())
                .create(CountriesViewModel.class);


        countriesViewModel.getAllCountries().observe(this, countries -> {

        });


        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    private void getCountryDetailed() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {


                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String capital = jsonObject.getString("capital");
                        String region = jsonObject.getString("region");
                        String subRegion = jsonObject.getString("subregion");
                        long population = jsonObject.getLong("population");
                        String flag = jsonObject.getString("flag");
                        JSONArray bordersArray = jsonObject.getJSONArray("borders");
//                        Log.d("++++++", "---" + bordersArray);
                        StringBuilder builder = new StringBuilder();
                        for(int k =0; k<bordersArray.length(); k++){
                            if (bordersArray.length()-1>k) {
                                builder.append(bordersArray.getString(k)).append(", ");
                            }else {
                                builder.append(bordersArray.getString(k));
                            }
//                            Log.d("++++++", "borders" + bordersArray.getString(k));
                        }
                        String border = builder.toString();
                        Log.d("++++++", "borders" + border);

                        StringBuilder stringBuilder = new StringBuilder();
                        JSONArray languagesArray =  jsonObject.getJSONArray("languages");
                        for (int k = 0; k<languagesArray.length(); k++){
                            JSONObject language = languagesArray.getJSONObject(k);
                            if (languagesArray.length()-1>k) {
                                stringBuilder.append(language.getString("name")).append(", ");
                            }
                            else {
                                stringBuilder.append(language.getString("name"));
                            }

                        }
                        String lang = stringBuilder.toString();



                        Countries c = new Countries(name, capital, flag, region, subRegion, population,border,lang);

                        countriesArrayList.add(c);
                        CountriesViewModel.insert(c);
                        setUpRecyclerView();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonArrayRequest);
//        queue.add(jsonArrayRequest);


    }

    private void setUpRecyclerView() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(countriesArrayList, this);

        binding.recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getCountryDetailed();
    }
}