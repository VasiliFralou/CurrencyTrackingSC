package by.vfdev.currencytrackingsc.UI

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.ViewModel.MainViewModel
import by.vfdev.currencytrackingsc.databinding.FragmentPopularBinding

class PopularFragment : Fragment(R.layout.fragment_popular) {

    private val mainVM: MainViewModel by activityViewModels()
    private val binding by viewBinding(FragmentPopularBinding::bind)
    private var date: String? = "-"

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainVM.getListCurrency(mainVM.selectCurrency.value.toString())

        val adapter = PopularAdapter(
            onClick = {
                mainVM.insertCurrencyFavorite(it)
                mainVM.getListCurrency(mainVM.selectCurrency.value.toString())
            }
        )

        var pos = 2

        binding.popularRV.adapter = adapter
        binding.popularRV.layoutManager = LinearLayoutManager(activity as MainActivity)

        mainVM.currencyLive.observe(viewLifecycleOwner) { list ->
            (binding.popularRV.adapter as PopularAdapter).updateData(list)
            date = list.date
            binding.datePopularTV.text = "Курс 1 ${mainVM.selectCurrency.value} на : $date"
        }

        fun sortList(sort: Int) {
            mainVM.currencyLive.observe(viewLifecycleOwner) { list ->
                (binding.popularRV.adapter as PopularAdapter).updateDataSort(list, sort)
            }
        }

        fun getSort(posit: Int) {
            pos += 1
            sortList(posit)
        }

        binding.btnSortPopular.setOnClickListener {
            when (pos) {
                1 -> { getSort(pos)
                    binding.btnSortPopular.setBackgroundResource(R.drawable.ic_sort_currency_up)
                    Toast.makeText(requireActivity(), "Валюта отсортирована по алфавиту (возврастанию)", Toast.LENGTH_SHORT).show()
                }
                2 -> { getSort(pos)
                    binding.btnSortPopular.setBackgroundResource(R.drawable.ic_sort_value_up)
                    Toast.makeText(requireActivity(), "Валюта отсортирована по алфавиту (убыванию)", Toast.LENGTH_SHORT).show()
                }
                3 -> { getSort(pos)
                    binding.btnSortPopular.setBackgroundResource(R.drawable.ic_sort_value_down)
                    Toast.makeText(requireActivity(), "Валюта отсортирована по значению (возврастанию)", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.btnSortPopular.setBackgroundResource(R.drawable.ic_sort_currency_down)
                    Toast.makeText(requireActivity(), "Валюта отсортирована по значению (убывание)", Toast.LENGTH_SHORT).show()
                    getSort(pos)
                    pos = 1
                }
            }
        }
    }
}