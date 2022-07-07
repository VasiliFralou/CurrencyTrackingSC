package by.vfdev.currencytrackingsc.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.currencytrackingsc.localmodel.currencyfavorite.CurrencyFavoriteData
import by.vfdev.currencytrackingsc.localmodel.currencyfavorite.CurrencyFavoriteEntity
import by.vfdev.currencytrackingsc.remotemodel.currency.CurrencyTrackingEntity
import by.vfdev.currencytrackingsc.repository.CurrencyFavoriteRepository
import by.vfdev.currencytrackingsc.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyFavoriteRepository: CurrencyFavoriteRepository
) : ViewModel() {

    var date: String? = "-"
    var pos = 2

    val currencyLive: MutableLiveData<CurrencyTrackingEntity> by lazy {
        MutableLiveData<CurrencyTrackingEntity>()
    }
    val currencyFavoriteLive: MutableLiveData<CurrencyTrackingEntity> by lazy {
        MutableLiveData<CurrencyTrackingEntity>()
    }

    private val symbolsFavorite = MutableLiveData<String>()
    val selectCurrency = MutableLiveData<String>()

    fun getListCurrency(currency: String) {
        viewModelScope.launch {
            val list = currencyRepository.getDataCurrency(currency, "")
            selectCurrency.value = currency
            list.onSuccess {
                currencyLive.value = it
            }.onFailure {
                currencyLive.value = null
                Log.e("!!!ErrorListCurrency", it.stackTraceToString())
            }
        }
    }

    fun insertCurrencyFavorite(currency: CurrencyFavoriteEntity) {
        viewModelScope.launch {
            currencyFavoriteRepository.insertCurrencyFavoriteFromDB(currency)
        }
    }

    fun deleteSelectCurrency(base: String) {
        viewModelScope.launch {
            currencyFavoriteRepository.deleteSelectCurrency(base)
        }
    }

    fun getListFavoriteCurrency(selectedCurrency: String) {
        viewModelScope.launch {

            val listFavoriteCurrency = currencyFavoriteRepository.getDataFavoriteCurrency()
            val stringFavoriteCurrency = entityToString(listFavoriteCurrency)

            symbolsFavorite.value = stringFavoriteCurrency
            if (!stringFavoriteCurrency.isNullOrEmpty()) {
                val listCurrency = currencyFavoriteRepository.getDataFavoriteCurrency(
                    selectedCurrency, stringFavoriteCurrency)
                listCurrency
                    .onSuccess {
                        currencyFavoriteLive.value = it
                    }
                    .onFailure {
                        currencyFavoriteLive.value = null
                        Log.e("!!!ErrorListCurrency", it.stackTraceToString())
                    }
            }
        }
    }

    private fun entityToString(listFavoriteCurrency: List<CurrencyFavoriteData>): String {

        val listName: MutableList<String> = mutableListOf()
        for (name in listFavoriteCurrency) {
            listName.add(name.base)
        }
        val newListCurrency = listName.toSet().toList()
        val separator = ","

        return newListCurrency.joinToString(separator)
    }
}