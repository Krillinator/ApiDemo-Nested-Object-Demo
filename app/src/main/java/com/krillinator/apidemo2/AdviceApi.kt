package com.krillinator.apidemo2

import retrofit2.Call
import retrofit2.http.GET

interface AdviceApi {
    @GET("advice")
    fun getInfo(): Call<AdviceSlip>
}