package by.vfdev.currencytrackingsc.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.localmodel.currencyfavorite.CurrencyFavoriteEntity
import by.vfdev.currencytrackingsc.remotemodel.currency.Rates
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.remotemodel.currency.CurrencyTrackingEntity
import by.vfdev.currencytrackingsc.databinding.ItemLayoutPopularBinding

class PopularAdapter (private val onClick: (base: CurrencyFavoriteEntity) -> Unit) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    private val list: MutableList<Rates> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding by viewBinding (ItemLayoutPopularBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_layout_popular, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.nameCurrencyTV.text = item.currency
        holder.binding.valueCurrencyTV.text = String.format("%.2f", item.value)

        holder.binding.btnFavorites.setOnClickListener {
            onClick.invoke(
                CurrencyFavoriteEntity(
                    list[holder.bindingAdapterPosition].currency
                )
            )
            holder.binding.btnFavorites.setBackgroundResource(R.drawable.ic_favorites)
        }
    }

    override fun getItemCount() = list.size

    fun updateData(rates: CurrencyTrackingEntity) {
        list.clear()
        list.addAll(rates.rates)
        notifyDataSetChanged()
    }

    fun updateDataSort(rates: CurrencyTrackingEntity, sort: Int) {
        list.clear()
        list.addAll(rates.rates)

        when (sort) {
            1 -> list.sortBy { it.currency }
            2 -> list.sortByDescending { it.currency }
            3 -> list.sortBy { it.value }
            4 -> list.sortByDescending { it.value }
        }
        notifyDataSetChanged()
    }
}