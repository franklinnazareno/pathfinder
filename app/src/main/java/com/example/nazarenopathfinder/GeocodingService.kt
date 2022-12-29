package com.example.nazarenopathfinder

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GeocodingService {
    @GET("https://api.mapbox.com/geocoding/v5/mapbox.places/{location}.json?")
    fun getGeocodingResponse(@Path("location") location:String, @Query("access_token") access_token: String):
        Call<GeocodingResponse>
}