package network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import domain.Current
import domain.State
import domain.Version
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import util.Log
import java.util.concurrent.TimeUnit

class Client(url: String, apiKey: String) {

    private val fail by lazy { Current(State.Unknown.name) }

    private val json by lazy { Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()) }

    private val interceptor = Interceptor {
        val request = it.request()
        val headers = request.headers.newBuilder().add("X-Api-Key", apiKey).build()
        val updated = request.newBuilder().headers(headers).build()
        it.proceed(updated)
    }

    private val http = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    private val api = Retrofit.Builder()
            .addConverterFactory(json)
            .client(http)
            .baseUrl(url)
            .build()
            .create(Api::class.java)

    suspend fun getVersion(): Version? =
        runCatching { api.version().bodyOrNull() }
            .onFailure { Log.panic(it) }
            .getOrNull()

    suspend fun getCurrent(): Current =
        runCatching { api.job().bodyOrNull() ?: fail }
            .onFailure { Log.panic(it) }
            .getOrDefault(fail)

    private fun <T> Response<T>.bodyOrNull(): T? =
        when {
            isSuccessful.not() -> { printErrorCode(); null }
            body() == null -> { printErrorCode(); null }
            else -> requireNotNull(body())
        }

    private fun <T> Response<T>.printErrorCode() = Log.append("Http request failed: ${this.code()}")
}
