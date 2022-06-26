package by.vfdev.currencytrackingsc.LocalModel

import android.content.Context
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTrackingEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = CurrencyDatabase.invoke(context).getCurrencyDao()

    suspend fun insertCurrency(currency: List<CurrencyTrackingEntity>) {

        database.deleteAllCurrency()
        database.insertCurrency(currency)
    }

    suspend fun getAllCurrency() : List<CurrencyTrackingEntity> {
        return database.getAllCurrency()
    }
}