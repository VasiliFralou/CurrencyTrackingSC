package by.vfdev.currencytrackingsc.api

import by.vfdev.currencytrackingsc.remotemodel.currency.CurrencyTrackingBean
import by.vfdev.currencytrackingsc.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiCurrency {

    // https:/api.apilayer.com/exchangerates_data/latest?symbols=&base=EUR&apikey=rrUrlh8FjQSN8RHlS5tTlBQhgABEZh4L
    @Headers("apikey: rrUrlh8FjQSN8RHlS5tTlBQhgABEZh4L")
    @GET("latest")
    suspend fun getCurrency(
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ) : CurrencyTrackingBean

    companion object {
        fun create(): ApiCurrency {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiCurrency::class.java)
        }
    }
}