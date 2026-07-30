package com.shudley.lottocombinationgenerator.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface LotteryService {

    @Headers(
        "x-rapidapi-key: YOUR_RAPIDAPI_KEY",
        "x-rapidapi-host: south-african-lottery-results-api.p.rapidapi.com"
    )
    @GET("api/get_latest_results")
    suspend fun getLatestResults(): Response<String>

    @Headers(
        "x-rapidapi-key: YOUR_RAPIDAPI_KEY",
        "x-rapidapi-host: south-african-lottery-results-api.p.rapidapi.com"
    )
    @GET("api/getLottoResults")
    suspend fun getLottoResults(): Response<String>

    @Headers(
        "x-rapidapi-key: YOUR_RAPIDAPI_KEY",
        "x-rapidapi-host: south-african-lottery-results-api.p.rapidapi.com"
    )
    @GET("api/getSAPowerballResults")
    suspend fun getPowerBallResults(): Response<String>

    @Headers(
        "x-rapidapi-key: YOUR_RAPIDAPI_KEY",
        "x-rapidapi-host: south-african-lottery-results-api.p.rapidapi.com"
    )
    @GET("api/getDailyLottoResults")
    suspend fun getDailyLottoResults(): Response<String>

    @Headers(
        "x-rapidapi-key: YOUR_RAPIDAPI_KEY",
        "x-rapidapi-host: south-african-lottery-results-api.p.rapidapi.com"
    )
    @GET("api/getLottoPlus1Results")
    suspend fun getLottoPlus1Results(): Response<String>

    @Headers(
        "x-rapidapi-key: YOUR_RAPIDAPI_KEY",
        "x-rapidapi-host: south-african-lottery-results-api.p.rapidapi.com"
    )
    @GET("api/getPowerballXtraResults")
    suspend fun getPowerballXtraResults(): Response<String>
}