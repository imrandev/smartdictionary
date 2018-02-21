package com.nerd.app.voisy.rest;

import com.nerd.app.voisy.model.AdditionalData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hp on 4/4/2017.
 */

public class YandexApiClient {
    public static final String BASE_URL = "https://translate.yandex.net";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
