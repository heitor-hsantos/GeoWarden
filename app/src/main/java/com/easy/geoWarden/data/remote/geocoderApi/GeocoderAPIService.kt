package com.easy.geoWarden.data.remote.geocoderApi

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface GeocoderAPIService {
    // repassar dados para dentro da requisição
    //provavelmente uma classe UserLocationPreset
    @GET("http://api.openweathermap.org/geo/1.0/direct?q=São Paulo&limit=1&appid={}")
    fun locationData(): Call<JsonObject>
}

