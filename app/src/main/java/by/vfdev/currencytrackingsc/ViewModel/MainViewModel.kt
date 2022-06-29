package by.vfdev.currencytrackingsc.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite.CurrencyFavoriteData
import by.vfdev.currencytrackingsc.RemoteModel.Currency.CurrencyTrackingEntity
import by.vfdev.currencytrackingsc.RemoteModel.Currency.Rates
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
    private val symbolsFavorite = MutableLiveData<String>()
    val selectCurrency = MutableLiveData<String>()

    private val _selectFavoriteLD = MutableLiveData<Rates>()
    val selectFavorite: LiveData<Rates> = _selectFavoriteLD

    fun onSelectFavorite(base: Rates) {
        _selectFavoriteLD.value = base
    }

    fun getListCurrency(currency: String, symbols: String) {
        viewModelScope.launch {
            val list = currencyRepository.getDataCurrency(currency, "")
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

    fun getListFavoriteCurrency(selectedCurrency: String) {
        viewModelScope.launch {
            val listFavoriteCurrency = currencyRepository.getDataFavoriteCurrency()
            val listName: MutableList<String> = mutableListOf()
            for(name in listFavoriteCurrency) {
                listName.add(name.base)
            }
            val newListCurrency = listName.toSet().toList()
            val separator = ","
            val list = newListCurrency.joinToString(separator)
            symbolsFavorite.postValue(list)
            if (!list.isNullOrEmpty()) {
                val listCurrency = currencyRepository.getDataCurrency(selectedCurrency, list)
                listCurrency
                    .onSuccess {
                        currencyFavoriteLive.postValue(it)
                    }
                    .onFailure {
                        currencyFavoriteLive.postValue(null)
                        Log.e("!!!ErrorListCurrency", it.stackTraceToString())
                    }
            }
        }
    }
}