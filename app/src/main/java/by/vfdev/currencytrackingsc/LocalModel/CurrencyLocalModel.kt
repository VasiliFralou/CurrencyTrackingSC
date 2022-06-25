package by.vfdev.currencytrackingsc.LocalModel

import android.content.Context
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTracking
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CurrencyLocalModel @Inject constructor(@ApplicationContext context: Context) {

    private val database = CurrencyDatabase.invoke(context).getCurrencyDao()

    suspend fun insertCurrency(currency: List<CurrencyTracking>) {

        database.deleteAllCurrency()
        database.insertCurrency(currency)
    }

    suspend fun getAllCurrency() : List<CurrencyTracking> {
        return database.getAllCurrency()
    }
}