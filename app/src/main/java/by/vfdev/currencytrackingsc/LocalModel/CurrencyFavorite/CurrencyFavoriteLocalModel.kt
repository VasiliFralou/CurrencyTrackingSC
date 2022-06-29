package by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyFavoriteLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = CurrencyFavoriteDatabase.getDataBase(context).currencyFavoriteDao()

    suspend fun insertOneFavoriteCurrency(favorite: CurrencyFavoriteData) {
        database.insertOneFavoriteCurrency(favorite)
    }

    suspend fun getAllFavoriteCurrency(): MutableList<CurrencyFavoriteData> {
        return database.getAllFavoriteCurrency()
    }

}