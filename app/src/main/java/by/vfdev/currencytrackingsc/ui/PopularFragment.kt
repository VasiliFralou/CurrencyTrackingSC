package by.vfdev.currencytrackingsc.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.viewmodel.MainViewModel
import by.vfdev.currencytrackingsc.databinding.FragmentPopularBinding

class PopularFragment : Fragment(R.layout.fragment_popular) {

    private val mainVM: MainViewModel by activityViewModels()
    private val binding by viewBinding(FragmentPopularBinding::bind)

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

        binding.popularRV.adapter = adapter
        binding.popularRV.layoutManager = LinearLayoutManager(requireActivity())

        mainVM.currencyLive.observe(viewLifecycleOwner) { list ->
            if (list != null) {
                adapter.updateData(list)
                mainVM.date = list.date
                binding.datePopularTV.text = "Курс 1 ${mainVM.selectCurrency.value} на : ${mainVM.date}"
            }
        }

        fun sortList(sort: Int) {
            mainVM.currencyLive.observe(viewLifecycleOwner) { list ->
                (binding.popularRV.adapter as PopularAdapter).updateDataSort(list, sort)
            }
        }

        fun getSort(posit: Int) {
            mainVM.pos += 1
            sortList(posit)
        }

        binding.btnSortPopular.setOnClickListener {
            when (mainVM.pos) {
                1 -> { getSort(mainVM.pos)
                    binding.btnSortPopular.setBackgroundResource(R.drawable.ic_sort_currency_up)
                    Toast.makeText(requireActivity(),
                        R.string.sortByCurrency, Toast.LENGTH_SHORT).show()
                }
                2 -> { getSort(mainVM.pos)
                    binding.btnSortPopular.setBackgroundResource(R.drawable.ic_sort_value_up)
                    Toast.makeText(requireActivity(),
                        R.string.sortByDescendingCurrency, Toast.LENGTH_SHORT).show()
                }
                3 -> { getSort(mainVM.pos)
                    binding.btnSortPopular.setBackgroundResource(R.drawable.ic_sort_value_down)
                    Toast.makeText(requireActivity(),
                        R.string.sortByValue, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.btnSortPopular.setBackgroundResource(R.drawable.ic_sort_currency_down)
                    Toast.makeText(requireActivity(),
                        R.string.sortByDescendingValue, Toast.LENGTH_SHORT).show()
                    getSort(mainVM.pos)
                    mainVM.pos = 1
                }
            }
        }
    }
}