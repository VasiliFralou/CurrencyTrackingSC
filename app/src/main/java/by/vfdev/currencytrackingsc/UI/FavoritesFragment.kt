package by.vfdev.currencytrackingsc.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.LocalModel.CurrencyFavorite.CurrencyFavoriteData
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.RemoteModel.Currency.Rates
import by.vfdev.currencytrackingsc.ViewModel.MainViewModel
import by.vfdev.currencytrackingsc.databinding.FragmentFavoritesBinding
import by.vfdev.currencytrackingsc.databinding.FragmentPopularBinding

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val mainVM: MainViewModel by activityViewModels()
    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val listFavoriteCurrencyLive = mutableListOf<Rates>()
    private var date: String? = "-"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainVM.currencyFavoriteLive.observe(activity as MainActivity) { list ->
            listFavoriteCurrencyLive.clear()
            listFavoriteCurrencyLive.addAll(list.rates)
            date = list.date
        }
        mainVM.selectCurrency.observe(activity as MainActivity) { currency ->
            binding.dateFavoriteTV.text = "Курс 1 ${mainVM.selectCurrency.value} на : $date"
        }

        val adapter = FavoritesAdapter(listFavoriteCurrencyLive)
        binding.favoriteRV.adapter = adapter
        binding.favoriteRV.layoutManager = LinearLayoutManager(activity as MainActivity)
    }
}