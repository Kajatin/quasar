package com.example.quasar.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun fetchApod(
    service: NasaService,
    date: String,
    onSuccess: (apod: ApodResponse) -> Unit,
    onError: (error: String) -> Unit
) {
    service.fetchApod(date).enqueue(
        object: Callback<ApodResponse> {
            override fun onResponse(call: Call<ApodResponse>, response: Response<ApodResponse>) {
                if (response.isSuccessful) {
                    Log.d("fetchApod", "${response.headers()}")
                    onSuccess(response.body()?: ApodResponse("0", "", "", "", "", ""))
                }
                else {
                    onError(response.raw().toString())
                }
            }

            override fun onFailure(call: Call<ApodResponse>, t: Throwable) {
                onError(t.message?: "Unknown error")
            }

        }
    )
}

interface NasaService {
    @GET("planetary/apod")
    fun fetchApod(@Query("date") date: String, @Query("api_key") key: String = API_KEY): Call<ApodResponse>
    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
        private const val API_KEY = "" // TODO: secure this key

        fun create(): NasaService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(NasaService::class.java)
        }
    }
}