package by.vfdev.currencytrackingsc.repository

import by.vfdev.currencytrackingsc.localmodel.currency.CurrencyLocalModel
import by.vfdev.currencytrackingsc.localmodel.currencyfavorite.CurrencyFavoriteData
import by.vfdev.currencytrackingsc.localmodel.currencyfavorite.CurrencyFavoriteEntity
import by.vfdev.currencytrackingsc.localmodel.currencyfavorite.CurrencyFavoriteLocalModel
import by.vfdev.currencytrackingsc.remotemodel.currency.CurrencyRemoteModel
import by.vfdev.currencytrackingsc.remotemodel.currency.CurrencyTrackingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyRemoteModel: CurrencyRemoteModel,
    private val currencyLocalModel: CurrencyLocalModel
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

    private suspend fun updateDataCurrencyFromBD(currency: CurrencyTrackingEntity?, base: String, symbols: String) :
            CurrencyTrackingEntity? {

        val entityUpdate = currencyRemoteModel.getCurrencyRemoteModel(base, symbols)
        if (currency != null) {
            currencyLocalModel.insertCurrency(currency)
        }

        return entityUpdate
    }
}