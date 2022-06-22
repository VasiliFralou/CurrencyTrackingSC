package by.vfdev.currencytrackingsc.RemoteModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "currency_table")
data class CurrencyTracking(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0
)