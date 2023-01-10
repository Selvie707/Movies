package com.example.themovie.data;

import java.util.ArrayList;

public class DataRecentlyWatched {
    public static String[][] data = new String[][] {
            {"DR. Stone", "2022", "https://i.pinimg.com/736x/ff/3c/e5/ff3ce5c5beaf47f6c571edb3810d4059.jpg"},

            {"DR. Stone", "2022", "https://i.pinimg.com/736x/ff/3c/e5/ff3ce5c5beaf47f6c571edb3810d4059.jpg"},

            {"DR. Stone", "2022", "https://i.pinimg.com/736x/ff/3c/e5/ff3ce5c5beaf47f6c571edb3810d4059.jpg"},

            {"DR. Stone", "2022", "https://i.pinimg.com/736x/ff/3c/e5/ff3ce5c5beaf47f6c571edb3810d4059.jpg"},

            {"DR. Stone", "2022", "https://i.pinimg.com/736x/ff/3c/e5/ff3ce5c5beaf47f6c571edb3810d4059.jpg"},

            {"DR. Stone", "2022", "https://i.pinimg.com/736x/ff/3c/e5/ff3ce5c5beaf47f6c571edb3810d4059.jpg"},

            {"DR. Stone", "2022", "https://i.pinimg.com/736x/ff/3c/e5/ff3ce5c5beaf47f6c571edb3810d4059.jpg"},

            {"DR. Stone", "2022", "https://i.pinimg.com/736x/ff/3c/e5/ff3ce5c5beaf47f6c571edb3810d4059.jpg"},

            {"DR. Stone", "2022", "https://i.pinimg.com/736x/ff/3c/e5/ff3ce5c5beaf47f6c571edb3810d4059.jpg"}
    };

    public  static ArrayList<RecentlyWatched> DataMovie() {
        ArrayList<RecentlyWatched> dataMovie = new ArrayList<>();
        for (String[] varData : data) {
            RecentlyWatched rwm = new RecentlyWatched();
            rwm.setTitle(varData[0]);
            rwm.setYear(varData[1]);
            rwm.setPhoto(varData[2]);

            dataMovie.add(rwm);
        }
        return dataMovie;
    }
}
