package by.vfdev.currencytrackingsc.LocalDataSource

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.vfdev.currencytrackingsc.DataSourse.Rates

@Entity(tableName = "currency_table")
class CurrencyTrackingData(
    @PrimaryKey val base: String,
    val date: String,
    val rates: List<Rates>,
    val timestamp: Int)