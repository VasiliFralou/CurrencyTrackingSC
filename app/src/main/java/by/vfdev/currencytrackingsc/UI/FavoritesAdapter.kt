package by.vfdev.currencytrackingsc.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.DataSourse.Rates
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.databinding.ItemLayoutPopularBinding

class FavoritesAdapter (private val listRates: MutableList<Rates>) :
    RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val binding by viewBinding (ItemLayoutPopularBinding::bind)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_layout_popular, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = listRates[position]

        holder.binding.nameCurrencyTV.text = item.currency
        holder.binding.valueCurrencyTV.text = item.value.toString()
    }

    override fun getItemCount() = listRates.size
}