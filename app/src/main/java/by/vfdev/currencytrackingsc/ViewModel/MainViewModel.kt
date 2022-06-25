package by.vfdev.currencytrackingsc.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTracking
import by.vfdev.currencytrackingsc.Repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository) : ViewModel() {

        init {
            getListCurrency()
        }

    val currencyLive: MutableLiveData<List<CurrencyTracking>> by lazy {
        MutableLiveData<List<CurrencyTracking>>()
    }

    fun getListCurrency() {
        viewModelScope.launch {
            val list = currencyRepository.getDataCurrency("USD")
            list.onSuccess {
                currencyLive.postValue(it)
                Log.e("!!!LIST", currencyLive.value.toString())
            }.onFailure {
                currencyLive.postValue(mutableListOf())
                Log.e("!!!ErrorListCurrency", it.stackTraceToString())
            }
        }
    }
}