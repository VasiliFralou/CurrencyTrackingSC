package by.vfdev.currencytrackingsc.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite.CurrencyFavoriteData
import by.vfdev.currencytrackingsc.RemoteModel.Currency.CurrencyTrackingEntity
import by.vfdev.currencytrackingsc.Repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository) : ViewModel() {

    val currencyLive: MutableLiveData<CurrencyTrackingEntity> by lazy {
        MutableLiveData<CurrencyTrackingEntity>()
    }
    val currencyFavoriteLive: MutableLiveData<CurrencyTrackingEntity> by lazy {
        MutableLiveData<CurrencyTrackingEntity>()
    }

    val symbolsCurrency = MutableLiveData<String>()
    val selectCurrency = MutableLiveData<String>()

    fun getListCurrency(currency: String, symbols: String) {
        viewModelScope.launch {
            val list = currencyRepository.getDataCurrency(currency, symbols)
            selectCurrency.postValue(currency)
            list.onSuccess {
                currencyLive.postValue(it)
            }.onFailure {
                currencyLive.postValue(null)
                Log.e("!!!ErrorListCurrency", it.stackTraceToString())
            }
        }
    }

    fun insertCurrencyFavorite(currency: CurrencyFavoriteData) {
        viewModelScope.launch {
            currencyRepository.insertCurrencyFavoriteFromDB(currency)
        }
    }

    fun getListFavoriteCurrency(currency: String, symbols: String) {

        viewModelScope.launch {

            val listFavoriteCurrency = currencyRepository.getDataFavoriteCurrency()
            val listName: MutableList<String> = mutableListOf()
            for(name in listFavoriteCurrency) {
                listName.add(name.base)
            }

            Log.e("!!!", listName.toString())

            val newListCurrency = listName.toSet().toList()

            val separator = ","
            val list = newListCurrency.joinToString(separator)
            // symbolsCurrency.postValue(list)

            Log.e("!!!", list)

//            val listCurrency = currencyRepository.getDataCurrency("PLN", list)
//            listCurrency
//                .onSuccess {
//                    currencyFavoriteLive.postValue(it)
//                }
//                .onFailure {
//                    currencyFavoriteLive.postValue(null)
//                    Log.e("!!!ErrorListCurrency", it.stackTraceToString())
//                }
        }
    }
}