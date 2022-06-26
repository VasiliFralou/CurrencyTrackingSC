package by.vfdev.currencytrackingsc.Api

import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTrackingEntity
import by.vfdev.currencytrackingsc.Utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiCurrency {

    // https://api.apilayer.com/exchangerates_data/latest?symbols=&base=EUR&apikey=7fp5QVYyR5GfCeIDnzUCT08zHgN6DGdc
    @Headers("apikey: 7fp5QVYyR5GfCeIDnzUCT08zHgN6DGdc")
    @GET("latest")
    suspend fun getCurrency(
        @Query("base") base: String
    ) : CurrencyTrackingEntity

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