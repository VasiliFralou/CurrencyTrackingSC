package by.vfdev.currencytrackingsc.Repository

import by.vfdev.currencytrackingsc.LocalModel.Currency.CurrencyLocalModel
import by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite.CurrencyFavoriteData
import by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite.CurrencyFavoriteLocalModel
import by.vfdev.currencytrackingsc.RemoteModel.Currency.CurrencyRemoteModel
import by.vfdev.currencytrackingsc.RemoteModel.Currency.CurrencyTrackingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyRemoteModel: CurrencyRemoteModel,
    private val currencyLocalModel: CurrencyLocalModel,
    private val currencyFavoriteLocalModel: CurrencyFavoriteLocalModel
) {

    suspend fun getDataCurrency(base: String, symbols: String) :
            Result<CurrencyTrackingEntity> = withContext(Dispatchers.IO) {

        var currencyItem = currencyRemoteModel.getCurrencyRemoteModel(base, symbols)

        if (currencyItem == null) {
            currencyItem = currencyLocalModel.getCurrency()
        } else {
            launch {
                updateDataCurrencyFromBD(currencyItem, base, symbols)
            }
        }
        return@withContext Result.success(currencyItem)
    }

    suspend fun getDataFavoriteCurrency() : MutableList<CurrencyFavoriteData> = withContext(Dispatchers.IO) {

        return@withContext currencyFavoriteLocalModel.getAllFavoriteCurrency()
    }

    private suspend fun updateDataCurrencyFromBD(currency: CurrencyTrackingEntity?, base: String, symbols: String) :
            CurrencyTrackingEntity? {

        val entityUpdate = currencyRemoteModel.getCurrencyRemoteModel(base, symbols)
        if (currency != null) {
            currencyLocalModel.insertCurrency(currency)
        }

        return entityUpdate
    }

    suspend fun insertCurrencyFavoriteFromDB(currency: CurrencyFavoriteData) = withContext(Dispatchers.IO) {
        currencyFavoriteLocalModel.insertOneFavoriteCurrency(currency)
    }
}