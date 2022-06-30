package by.vfdev.currencytrackingsc.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.currencytrackingsc.localmodel.CurrencyFavorite.CurrencyFavoriteData
import by.vfdev.currencytrackingsc.localmodel.CurrencyFavorite.CurrencyFavoriteEntity
import by.vfdev.currencytrackingsc.RemoteModel.Currency.CurrencyTrackingEntity
import by.vfdev.currencytrackingsc.Repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository) : ViewModel() {

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
            selectCurrency.postValue(currency)
            list.onSuccess {
                currencyLive.postValue(it)
            }.onFailure {
                currencyLive.postValue(null)
                Log.e("!!!ErrorListCurrency", it.stackTraceToString())
            }
        }
    }

    fun insertCurrencyFavorite(currency: CurrencyFavoriteEntity) {
        viewModelScope.launch {
            currencyRepository.insertCurrencyFavoriteFromDB(currency)
        }
    }

    fun deleteSelectCurrency(base: String) {
        viewModelScope.launch {
            currencyRepository.deleteSelectCurrency(base)
        }
    }

    fun getListFavoriteCurrency(selectedCurrency: String) {
        viewModelScope.launch {

            val listFavoriteCurrency = currencyRepository.getDataFavoriteCurrency()
            val stringFavoriteCurrency = entityToString(listFavoriteCurrency)

            symbolsFavorite.postValue(stringFavoriteCurrency)
            if (!stringFavoriteCurrency.isNullOrEmpty()) {
                val listCurrency = currencyRepository.getDataFavoriteCurrency(selectedCurrency, stringFavoriteCurrency)
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