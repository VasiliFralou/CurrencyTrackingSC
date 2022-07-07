package by.vfdev.currencytrackingsc.localmodel.currencyfavorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ICurrencyFavoriteDao {

    @Insert
    suspend fun insertOneFavoriteCurrency(favorite: CurrencyFavoriteData)

    @Query("DELETE FROM currency_favorite_table WHERE :base LIKE base")
    suspend fun deleteSelectCurrency(base: String)

    @Query("SELECT * FROM currency_favorite_table")
    suspend fun getAllFavoriteCurrency(): List<CurrencyFavoriteData>
}