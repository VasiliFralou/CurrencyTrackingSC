package by.vfdev.currencytrackingsc.Repository

import by.vfdev.currencytrackingsc.LocalModel.CurrencyLocalModel
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyRemoteModel
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTrackingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyRemoteModel: CurrencyRemoteModel,
    private val currencyLocalModel: CurrencyLocalModel) {

    suspend fun getDataCurrency(base: String) : Result<List<CurrencyTrackingEntity>> = withContext(Dispatchers.IO) {

        var currencyList = currencyRemoteModel.getCurrencyRemoteModel(base)

        if (currencyList.isNullOrEmpty()) {
            currencyList = currencyLocalModel.getAllCurrency()
        } else {
            launch {
                updateDataCurrencyFromDB(base)
            }
        }
        return@withContext Result.success(currencyList)
    }

    private suspend fun updateDataCurrencyFromDB(base: String) : List<CurrencyTrackingEntity> {

        val currencyList = currencyRemoteModel.getCurrencyRemoteModel(base)
        currencyLocalModel.insertCurrency(currencyList)

        return currencyList
    }
}