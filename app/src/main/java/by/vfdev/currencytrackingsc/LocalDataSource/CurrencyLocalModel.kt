package by.vfdev.currencytrackingsc.LocalDataSource

import android.content.Context
import by.vfdev.currencytrackingsc.DataSourse.CurrencyTrackingEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = CurrencyDatabase.getDataBase(context).getCurrencyDao()

    fun getCurrency() : CurrencyTrackingEntity {
        return database.getAllCurrency()
    }

    fun insertCurrency(currency: CurrencyTrackingEntity) {

        val entity = CurrencyTrackingData(
            base = currency.base,
            date = currency.date,
            rates = currency.rates,
            timestamp = currency.timestamp)

        database.deleteAllCurrency()
        database.insertCurrency(entity)
    }
}