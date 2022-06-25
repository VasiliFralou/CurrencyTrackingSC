package by.vfdev.currencytrackingsc.RemoteModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "currency_table")
data class CurrencyTracking(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Rates,
    @SerializedName("timestamp")
    val timestamp: Int,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0
)