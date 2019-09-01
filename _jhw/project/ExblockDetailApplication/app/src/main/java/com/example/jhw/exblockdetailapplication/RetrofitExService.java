package com.example.jhw.exblockdetailapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitExService {

    String URL = "https://apis.openapi.sk.com";
    @GET("/tmap/pois/{poiId}")
    Call<Detail> getData(@Path("poiId") String poiId, @Query("version") int version, @Query("appKey") String appKey);


}
