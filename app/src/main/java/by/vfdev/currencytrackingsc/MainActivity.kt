package by.vfdev.currencytrackingsc

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.vfdev.currencytrackingsc.Api.ApiCurrency
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTracking
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val uiScope = MainScope()

    val apiCurrency = ApiCurrency.create()
    val currency = mutableListOf<CurrencyTracking>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrency()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getCurrency() {
        uiScope.launch {
            try {
                currency.clear()
                currency.addAll(listOf(apiCurrency.getCurrency( "BYN")))
                Log.e("!!!", currency.toString())
            } catch (e: Exception) {
                Log.d("!!!", e.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        uiScope.cancel()
    }
}