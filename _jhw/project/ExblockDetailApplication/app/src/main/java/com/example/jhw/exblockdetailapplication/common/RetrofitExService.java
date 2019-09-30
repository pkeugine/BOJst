package com.example.jhw.exblockdetailapplication.common;

import com.example.jhw.exblockdetailapplication.data.Detail;
import com.example.jhw.exblockdetailapplication.data.Review;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitExService {
    // http 통신
    String Tmap_URL = "https://apis.openapi.sk.com";
    String DB_URL = "http://192.168.1.239:8000";
    @GET("/tmap/pois/{poiId}")
    Call<Detail> getDetailData(@Path("poiId") String poiId, @Query("version") int version, @Query("appKey") String appKey);

    @GET("/tmap/pois/search/around")
    Call<JsonObject> getPOIData(@Query("version") int version, @Query("page") int page, @Query("count") int count, @Query("categories") String categories,
                                @Query("centerLon") double centerLon, @Query("centerLat") double centerLat, @Query("radius") int radius, @Query("appKey") String appKey);

    @POST("/mavenweb/{jsp}")
    Call<List<Review>> useDB(@Path("jsp") String jsp, @Query("placeId") int placeId);

}
