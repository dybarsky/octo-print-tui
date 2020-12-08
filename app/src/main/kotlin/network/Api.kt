package network

import domain.Current
import kotlinx.serialization.json.Json
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("api/version")
    suspend fun version(): Response<Json>

    @GET("api/job")
    suspend fun job(): Response<Current>

}