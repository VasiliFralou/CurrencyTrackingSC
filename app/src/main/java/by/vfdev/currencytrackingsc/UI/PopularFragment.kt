package by.vfdev.currencytrackingsc.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.RemoteModel.Currency.Rates
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.ViewModel.MainViewModel
import by.vfdev.currencytrackingsc.databinding.FragmentPopularBinding

class PopularFragment : Fragment(R.layout.fragment_popular) {

    private val mainVM: MainViewModel by activityViewModels()
    private val binding by viewBinding(FragmentPopularBinding::bind)
    private val listCurrencyLive = mutableListOf<Rates>()
    private var date: String? = "-"

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainVM.selectCurrency.observe(activity as MainActivity) { currency ->
            binding.datePopularTV.text = "Курс 1 ${mainVM.selectCurrency.value} на : $date"
        }

        mainVM.currencyLive.observe(activity as MainActivity) { list ->
            listCurrencyLive.clear()
            listCurrencyLive.addAll(list.rates)
            date = list.date
            binding.popularRV.adapter?.notifyDataSetChanged()
        }

        val adapter = PopularAdapter(listCurrencyLive)
        binding.popularRV.adapter = adapter
        binding.popularRV.layoutManager = LinearLayoutManager(activity as MainActivity)
    }
}
