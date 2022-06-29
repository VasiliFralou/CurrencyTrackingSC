package by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ICurrencyFavoriteDao {

    @Insert
    fun insertOneFavoriteCurrency(favorite: CurrencyFavoriteData)

    @Query("DELETE FROM currency_favorite_table WHERE :base LIKE base")
    fun deleteSelectCurrency(base: String)

    @Query("SELECT * FROM currency_favorite_table")
    fun getAllFavoriteCurrency(): MutableList<CurrencyFavoriteData>
}