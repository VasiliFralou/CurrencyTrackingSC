package by.vfdev.currencytrackingsc.localmodel.currencyfavorite

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyFavoriteLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = CurrencyFavoriteDatabase.getDataBase(context).currencyFavoriteDao()

    suspend fun getAllFavoriteCurrency(): List<CurrencyFavoriteData> {
        return database.getAllFavoriteCurrency()
    }

    suspend fun insertOneFavoriteCurrency(favorite: CurrencyFavoriteEntity) {

        val entity = CurrencyFavoriteData(
            base = favorite.base)

        database.insertOneFavoriteCurrency(entity)
    }

    suspend fun deleteSelectCurrency(base: String) {
        database.deleteSelectCurrency(base)
    }
}