package by.vfdev.currencytrackingsc.Api

import by.vfdev.currencytrackingsc.RemoteModel.Currency.CurrencyTrackingBean
import by.vfdev.currencytrackingsc.Utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiCurrency {

    // https:/api.apilayer.com/exchangerates_data/latest?symbols=&base=EUR&apikey=OUJse8OIyG0YMkNLYVUORrDewbETw9IM
    @Headers("apikey: OUJse8OIyG0YMkNLYVUORrDewbETw9IM")
    @GET("latest")
    suspend fun getCurrency(
        @Query("base") base: String
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