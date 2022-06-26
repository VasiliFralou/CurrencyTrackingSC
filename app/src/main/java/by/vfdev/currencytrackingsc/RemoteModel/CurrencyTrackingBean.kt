package by.vfdev.currencytrackingsc.RemoteModel

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

class CurrencyTrackingBean(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Map<String, Double>,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Int)