package com.example.asiacountries.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



    @Entity(tableName = "Countries_detailed_table")
    public class Countries {

        @PrimaryKey(autoGenerate = true)

        private int id;
        @ColumnInfo(name = "name")
        private String name;
        @ColumnInfo(name = "capital")
        private String capital;
        @ColumnInfo(name = "flag")
        private String flag;
        @ColumnInfo(name = "region")
        private String region;
        @ColumnInfo(name = "subRegion")
        private String subRegion;
        @ColumnInfo(name = "population")
        private long population;
        @ColumnInfo(name = "borders")
        private String borders;
        @ColumnInfo(name = "languages")
        private String languages;




        public Countries(String name, String capital, String flag, String region, String subRegion, long population,String borders, String languages) {
            this.name = name;
            this.capital = capital;
            this.flag = flag;
            this.region = region;
            this.subRegion = subRegion;
            this.population = population;
            this.borders = borders;
            this.languages = languages;
        }

        public Countries() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCapital() {
            return capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getSubRegion() {
            return subRegion;
        }

        public void setSubRegion(String subRegion) {
            this.subRegion = subRegion;
        }

        public long getPopulation() {
            return population;
        }

        public void setPopulation(long population) {
            this.population = population;
        }
        //
        public String getBorders() {
            return borders;
        }
        //
        public void setBorders(String borders) {
            this.borders = borders;
        }

        public String getLanguages() {
            return languages;
        }

        public void setLanguages(String languages) {
            this.languages = languages;
        }
    }


