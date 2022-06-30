package by.vfdev.currencytrackingsc.localmodel.currency

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.vfdev.currencytrackingsc.RemoteModel.Currency.Rates

@Entity(tableName = "currency_table")
class CurrencyTrackingData(
    val base: String,
    val date: String,
    val rates: List<Rates>,
    val timestamp: Int,
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0)