package by.vfdev.currencytrackingsc.LocalDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.currencytrackingsc.DataSourse.CurrencyTrackingEntity

@Dao
interface ICurrencyDao {

    @Insert
    fun insertCurrency(currency: CurrencyTrackingData)

    @Query("SELECT * FROM currency_table")
    fun getAllCurrency() : CurrencyTrackingEntity

    @Query("DELETE FROM currency_table")
    fun deleteAllCurrency()
}