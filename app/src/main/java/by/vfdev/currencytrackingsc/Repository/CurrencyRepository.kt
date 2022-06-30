package by.vfdev.currencytrackingsc.Repository

import by.vfdev.currencytrackingsc.localmodel.currency.CurrencyLocalModel
import by.vfdev.currencytrackingsc.localmodel.CurrencyFavorite.CurrencyFavoriteData
import by.vfdev.currencytrackingsc.localmodel.CurrencyFavorite.CurrencyFavoriteEntity
import by.vfdev.currencytrackingsc.localmodel.CurrencyFavorite.CurrencyFavoriteLocalModel
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

    suspend fun getDataFavoriteCurrency(base: String, symbols: String) :
            Result<CurrencyTrackingEntity?> = withContext(Dispatchers.IO) {

        val currencyItem = currencyRemoteModel.getCurrencyRemoteModel(base, symbols)

        return@withContext Result.success(currencyItem)
    }

    suspend fun getDataFavoriteCurrency() : List<CurrencyFavoriteData> = withContext(Dispatchers.IO) {

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

    suspend fun insertCurrencyFavoriteFromDB(currency: CurrencyFavoriteEntity) = withContext(Dispatchers.IO) {
        currencyFavoriteLocalModel.insertOneFavoriteCurrency(currency)
    }

    suspend fun deleteSelectCurrency(base: String) = withContext(Dispatchers.IO) {
        currencyFavoriteLocalModel.deleteSelectCurrency(base)
    }
}