package by.vfdev.currencytrackingsc.RemoteModel.Currency

import by.vfdev.currencytrackingsc.Api.ApiCurrency
import javax.inject.Inject

class CurrencyRemoteModel @Inject constructor() {

    private val apiCurrency = ApiCurrency.create()

    suspend fun getCurrencyRemoteModel(base: String, symbols: String) : CurrencyTrackingEntity? {
        return try {

            val bean: CurrencyTrackingBean = apiCurrency.getCurrency(base, symbols)

            val entries: List<Rates> = bean.rates.toList().map { Rates(it.first, it.second) }

            val entity = CurrencyTrackingEntity(
                base = bean.base,
                date = bean.date,
                entries,
                timestamp = bean.timestamp)
            entity
        } catch (e: Exception) {
            return null
        }
    }
}