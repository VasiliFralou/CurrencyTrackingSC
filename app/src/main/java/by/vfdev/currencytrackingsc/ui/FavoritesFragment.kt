package by.vfdev.currencytrackingsc.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.viewmodel.MainViewModel
import by.vfdev.currencytrackingsc.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val mainVM: MainViewModel by activityViewModels()
    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private var date: String? = "-"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainVM.getListFavoriteCurrency(mainVM.selectCurrency.value.toString())

        val adapter = FavoritesAdapter(
            onClick = {
                mainVM.deleteSelectCurrency(it)
                mainVM.getListFavoriteCurrency(mainVM.selectCurrency.value.toString())
            }
        )
        binding.favoriteRV.adapter = adapter
        binding.favoriteRV.layoutManager = LinearLayoutManager(activity as MainActivity)

        mainVM.currencyFavoriteLive.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                adapter.updateData(list)
                date = list.date
                binding.dateFavoriteTV.text = "Курс 1 ${mainVM.selectCurrency.value} на : $date"
            } else { Toast.makeText(requireActivity(), R.string.error, Toast.LENGTH_SHORT).show() }
        }
    }
}