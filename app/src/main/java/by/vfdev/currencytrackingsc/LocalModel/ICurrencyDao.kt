package by.vfdev.currencytrackingsc.LocalModel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTracking

@Dao
interface ICurrencyDao {

    @Insert
    fun insertCurrency(currency: List<CurrencyTracking>)

    @Query("SELECT * FROM currency_table")
    fun getAllCurrency() : List<CurrencyTracking>

    @Query("DELETE FROM currency_table")
    fun deleteAllCurrency()
}