package network

import domain.Current
import domain.Version
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("api/version")
    suspend fun version(): Response<Version>

    @GET("api/job")
    suspend fun job(): Response<Current>

}