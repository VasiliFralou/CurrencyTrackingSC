package by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyFavoriteLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = CurrencyFavoriteDatabase.getDataBase(context).currencyFavoriteDao()

    fun getAllFavoriteCurrency(): MutableList<CurrencyFavoriteData> {
        return database.getAllFavoriteCurrency()
    }

    fun insertOneFavoriteCurrency(favorite: CurrencyFavoriteEntity) {

        val entity = CurrencyFavoriteData(
            base = favorite.base)

        database.insertOneFavoriteCurrency(entity)
    }

    fun deleteSelectCurrency(base: String) {
        database.deleteSelectCurrency(base)
    }
}