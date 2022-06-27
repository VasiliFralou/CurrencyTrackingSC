package by.vfdev.currencytrackingsc.Repository

import by.vfdev.currencytrackingsc.LocalModel.Currency.CurrencyLocalModel
import by.vfdev.currencytrackingsc.RemoteModel.Currency.CurrencyRemoteModel
import by.vfdev.currencytrackingsc.RemoteModel.Currency.CurrencyTrackingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyRemoteModel: CurrencyRemoteModel,
    private val currencyLocalModel: CurrencyLocalModel
) {

    suspend fun getDataCurrency(base: String) :
            Result<CurrencyTrackingEntity> = withContext(Dispatchers.IO) {

        var currencyItem = currencyRemoteModel.getCurrencyRemoteModel(base)

        if (currencyItem == null) {
            currencyItem = currencyLocalModel.getCurrency()
        } else {
            launch {
                updateDataCurrencyFromBD(currencyItem, base)
            }
        }
        return@withContext Result.success(currencyItem)
    }

    private suspend fun updateDataCurrencyFromBD(currency: CurrencyTrackingEntity?, base: String) :
            CurrencyTrackingEntity? {

        val entityUpdate = currencyRemoteModel.getCurrencyRemoteModel(base)
        if (currency != null) {
            currencyLocalModel.insertCurrency(currency)
        }

        return entityUpdate
    }
}