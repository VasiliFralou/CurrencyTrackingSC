package by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ICurrencyFavoriteDao {

    @Insert
    fun insertOneFavoriteCurrency(favorite: CurrencyFavoriteData)

    @Query("SELECT * FROM currency_favorite_table")
    fun getAllFavoriteCurrency(): MutableList<CurrencyFavoriteData>

    @Query("DELETE FROM currency_favorite_table WHERE base =:base")
    fun deleteSelectCurrency(base: String) : CurrencyFavoriteData
}