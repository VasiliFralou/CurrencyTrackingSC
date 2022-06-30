package by.vfdev.currencytrackingsc.localmodel.currency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.currencytrackingsc.remotemodel.currency.CurrencyTrackingEntity

@Dao
interface ICurrencyDao {

    @Insert
    fun insertCurrency(currency: CurrencyTrackingData)

    @Query("SELECT * FROM currency_table")
    fun getAllCurrency() : CurrencyTrackingEntity

    @Query("DELETE FROM currency_table")
    fun deleteAllCurrency()
}