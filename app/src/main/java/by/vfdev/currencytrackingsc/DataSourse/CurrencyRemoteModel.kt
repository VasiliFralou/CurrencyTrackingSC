package by.vfdev.currencytrackingsc.DataSourse

import android.util.Log
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class CurrencyRemoteModel @Inject constructor() {

    private val apiCurrency = ApiCurrency.create()

    suspend fun getCurrencyRemoteModel(base: String) : CurrencyTrackingEntity? {
        return try {

            val bean: CurrencyTrackingBean = apiCurrency.getCurrency(base)

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