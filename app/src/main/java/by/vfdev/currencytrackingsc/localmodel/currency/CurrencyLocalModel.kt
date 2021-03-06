package by.vfdev.currencytrackingsc.localmodel.currency

import android.content.Context
import by.vfdev.currencytrackingsc.remotemodel.currency.CurrencyTrackingEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = CurrencyDatabase.getDataBase(context).getCurrencyDao()

    suspend fun getCurrency() : CurrencyTrackingEntity {
        return database.getAllCurrency()
    }

    suspend fun insertCurrency(currency: CurrencyTrackingEntity) {

        val entity = CurrencyTrackingData(
            base = currency.base,
            date = currency.date,
            rates = currency.rates,
            timestamp = currency.timestamp)

        database.deleteAllCurrency()
        database.insertCurrency(entity)
    }
}