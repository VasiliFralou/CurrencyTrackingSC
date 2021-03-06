package by.vfdev.currencytrackingsc.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.remotemodel.currency.CurrencyTrackingEntity
import by.vfdev.currencytrackingsc.remotemodel.currency.Rates
import by.vfdev.currencytrackingsc.databinding.ItemLayoutFavoritesBinding

class FavoritesAdapter (private val onClick: (base: String) -> Unit) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private val list: MutableList<Rates> = mutableListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding by viewBinding(ItemLayoutFavoritesBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_layout_favorites, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.binding.nameCurrencyFavoriteTV.text = item.currency
        holder.binding.valueCurrencyFavoriteTV.text = String.format("%.2f", item.value)

        holder.itemView.setOnLongClickListener {
            onClick.invoke(item.currency)
            Toast.makeText(holder.itemView.context,"Валюта ${item.currency} удалена", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount() = list.size

    fun updateData(rates: CurrencyTrackingEntity) {
        list.clear()
        list.addAll(rates.rates)
        notifyDataSetChanged()
    }
}