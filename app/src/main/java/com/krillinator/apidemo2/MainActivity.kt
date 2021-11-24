package com.krillinator.apidemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        * Chains validation failed?
        * Start app in Cold Boot mode
        * SSL certificate for HTTPS expired
        * */


        val results = findViewById<TextView>(R.id.result)

        // Retrofit Builder - FIRST STEP
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.adviceslip.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        // Initializing Steps
        val service = retrofit.create(AdviceApi::class.java)
        val call = service.getInfo()

        // Boiler-plate kod (retrofit)
        call.enqueue(object : Callback<AdviceSlip> {

            // If API succeeds
            override fun onResponse(call: Call<AdviceSlip>, response: Response<AdviceSlip>) {
                if (response.isSuccessful) {

                    val advice = response.body()!! // response.body = Svaret
                    val stringBuilder = "Advice: \n link: ${advice.slip.advice} \n link: ${advice.slip.id}"

                    // Set Text
                    results.text = stringBuilder
                }
            }

            // If API fails
            override fun onFailure(call: Call<AdviceSlip>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.e("Debug Message",t.stackTraceToString())
            }
        })
    }
}