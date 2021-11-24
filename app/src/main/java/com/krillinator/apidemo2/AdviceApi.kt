package com.krillinator.apidemo2

import retrofit2.Call
import retrofit2.http.GET

interface AdviceApi {

    // Endpoint for URL
    @GET("advice")

    // The magic - Object passas in
    fun getInfo(): Call<AdviceSlip>
}



