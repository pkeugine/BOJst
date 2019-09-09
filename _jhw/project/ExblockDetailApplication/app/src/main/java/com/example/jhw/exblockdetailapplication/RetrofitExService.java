package com.example.jhw.exblockdetailapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitExService {
    // http 통신
    String Tmap_URL = "https://apis.openapi.sk.com";
    String DB_URL = "http://192.168.1.70:8000";
    @GET("/tmap/pois/{poiId}")
    Call<Detail> getData(@Path("poiId") String poiId, @Query("version") int version, @Query("appKey") String appKey);
    @POST("/mavenweb/{jsp}")
    Call<List<Review>> useDB(@Path("jsp") String jsp, @Query("placeId") int placeId);

}
