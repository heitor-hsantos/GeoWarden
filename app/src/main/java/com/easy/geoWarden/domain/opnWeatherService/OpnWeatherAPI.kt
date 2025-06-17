package com.easy.geoWarden.domain.opnWeatherService

import android.content.ContentValues.TAG
import com.easy.geoWarden.data.remote.geocoderApi.GeocoderAPIService
import com.easy.geoWarden.utils.NetworkUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import timber.log.Timber.Forest.tag

class OpnWeatherAPI {
    fun locationDetails(){
        val retrofitClient = NetworkUtils.getRetrofitInstace("")
        val endpoint = retrofitClient.create(GeocoderAPIService::class.java)
        var data = mutableListOf<String>()



        endpoint.locationData().enqueue(object : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                response.body()?.keySet()?.iterator()?.forEach { data.add(it) }

                Timber.plant(Timber.DebugTree())
                tag(TAG).w(" Hit 1 ${data.count()}")


            }

            override fun onFailure(p0: Call<JsonObject>, p1: Throwable) {
                Timber.plant(Timber.DebugTree())
                tag(TAG).w(" erro ")

            }
        })

        println("$data")
    }
}