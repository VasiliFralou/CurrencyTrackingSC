package by.vfdev.currencytrackingsc.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.RemoteModel.Currency.Rates
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.databinding.ItemLayoutPopularBinding

class PopularAdapter (private val listRates: MutableList<Rates>) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

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
        var favorites = false

        holder.binding.nameCurrencyTV.text = item.currency
        holder.binding.valueCurrencyTV.text = item.value.toString()

        holder.binding.btnFavorites.setOnClickListener {
            favorites = if (!favorites) {
                holder.binding.btnFavorites.setBackgroundResource(R.drawable.ic_favorites)
                true
            } else {
                holder.binding.btnFavorites.setBackgroundResource(R.drawable.ic_favorites_false)
                false
            }
        }
    }

    override fun getItemCount() = listRates.size
}