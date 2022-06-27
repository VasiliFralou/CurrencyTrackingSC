package by.vfdev.currencytrackingsc.RemoteModel.Currency

import com.google.gson.annotations.SerializedName

class CurrencyTrackingBean(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Map<String, Double>,
    @SerializedName("timestamp")
    val timestamp: Int)