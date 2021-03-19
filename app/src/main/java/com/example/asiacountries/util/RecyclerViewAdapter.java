package com.example.asiacountries.util;


import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.example.asiacountries.MainActivity;
import com.example.asiacountries.R;
import com.example.asiacountries.data.CountriesDao;
import com.example.asiacountries.model.Countries;
import com.example.asiacountries.model.CountriesViewModel;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Countries> list;
    private final Context context;

    public RecyclerViewAdapter(ArrayList<Countries> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.countries_layout, parent, false);


        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {



       Countries countries = list.get(position);


        holder.countryName.setText(countries.getName());
        holder.countryCapital.setText(countries.getCapital());
        holder.countryRegion.setText(countries.getRegion());
        holder.countrySubRegion.setText(countries.getSubRegion());
        holder.countryPopulation.setText(String.valueOf( countries.getPopulation()));
        holder.countryBorders.setText(countries.getBorders());
        holder.countryLanguages.setText(countries.getLanguages());

        String url = countries.getFlag();
//        holder.countryFlag.setImageURI(Uri.parse(url));
//        Log.d("image","()()("+url);
//
//        holder.countryFlag.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        Picasso.get().load(url).fit().centerCrop().into(holder.countryFlag);
//holder.countryFlag.setImageURI(Uri.parse(url));








    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView countryName;
        public TextView countryCapital;
        public TextView countryRegion;
        public TextView countrySubRegion;
        public TextView countryPopulation;
        public TextView countryBorders;
        public TextView countryLanguages;
        public ImageView countryFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryName = itemView.findViewById(R.id.countryName);
            countryCapital = itemView.findViewById(R.id.countryCapital);
            countryRegion = itemView.findViewById(R.id.countryRegion);
            countrySubRegion = itemView.findViewById(R.id.countrySubRegion);
            countryPopulation = itemView.findViewById(R.id.countryPopulation);
            countryBorders = itemView.findViewById(R.id.countryBorders);
            countryLanguages = itemView.findViewById(R.id.countryLanguages);
            countryFlag = itemView.findViewById(R.id.countryFlag);
        }
    }
}
