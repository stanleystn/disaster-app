package com.example.disasterapp.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://data.petabencana.id"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface GetData {
    @GET("reports")
    suspend fun getDisasterData(
        @Query("admin") area:String?,
        @Query("disaster") disaster:String?,
        @Query("timeperiod") timeRange:Int?
    ):DisasterData
}

object Report {
    val retrofitService: GetData by lazy {
        retrofit.create(GetData::class.java)
    }
}