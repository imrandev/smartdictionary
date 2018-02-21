package com.nerd.app.voisy.rest;

import com.nerd.app.voisy.model.AdditionalData;
import com.nerd.app.voisy.model.Sense;
import com.nerd.app.voisy.model.TranslateModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by hp on 4/4/2017.
 */

public interface ApiInterface {
    @GET
    Call<TranslateModel> getTranslateData(@Url String URL);

    @GET
    Call<AdditionalData> getHeadData(@Url String URL);
}
