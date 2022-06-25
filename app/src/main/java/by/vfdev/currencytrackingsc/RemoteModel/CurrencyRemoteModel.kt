package by.vfdev.currencytrackingsc.RemoteModel

import by.vfdev.currencytrackingsc.Api.ApiCurrency
import javax.inject.Inject

class CurrencyRemoteModel @Inject constructor() {

    private val apiCurrency = ApiCurrency.create()

    suspend fun getCurrencyRemoteModel(base: String) : List<CurrencyTracking> {
        return try {
            val currency: List<CurrencyTracking> = listOf(apiCurrency.getCurrency(base))
            currency
        } catch (e: Exception) {
            mutableListOf()
        }
    }
}