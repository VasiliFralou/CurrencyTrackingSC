package by.vfdev.currencytrackingsc.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.vfdev.currencytrackingsc.RemoteModel.Currency.CurrencyTrackingEntity
import by.vfdev.currencytrackingsc.Repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository) : ViewModel() {

        init {
            getListCurrency("AUD")
        }

    val currencyLive: MutableLiveData<CurrencyTrackingEntity> by lazy {
        MutableLiveData<CurrencyTrackingEntity>()
    }

    val selectCurrency = MutableLiveData<String>()

    fun getListCurrency(currency: String) {
        viewModelScope.launch {
            val list = currencyRepository.getDataCurrency(currency)
            selectCurrency.postValue(currency)
            list.onSuccess {
                currencyLive.postValue(it)
            }.onFailure {
                currencyLive.postValue(null)
                Log.e("!!!ErrorListCurrency", it.stackTraceToString())
            }
        }
    }
}