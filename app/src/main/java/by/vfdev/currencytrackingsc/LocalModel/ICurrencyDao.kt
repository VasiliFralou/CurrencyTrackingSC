package by.vfdev.currencytrackingsc.LocalModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTrackingEntity

@Dao
interface ICurrencyDao {

    @Insert
    fun insertCurrency(currency: List<CurrencyTrackingEntity>)

    @Query("SELECT * FROM currency_table")
    fun getAllCurrency() : List<CurrencyTrackingEntity>

    @Query("DELETE FROM currency_table")
    fun deleteAllCurrency()
}