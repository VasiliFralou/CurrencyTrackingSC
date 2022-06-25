package by.vfdev.currencytrackingsc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.Api.ApiCurrency
import by.vfdev.currencytrackingsc.RemoteModel.CurrencyTracking
import by.vfdev.currencytrackingsc.ViewModel.MainViewModel
import by.vfdev.currencytrackingsc.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val uiScope = MainScope()
    val apiCurrency = ApiCurrency.create()
    val currency = mutableListOf<CurrencyTracking>()

    private val mainVM: MainViewModel by viewModels()
    private val binding by viewBinding(ActivityMainBinding::bind)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainVM.getListCurrency()
    }
}