package by.vfdev.currencytrackingsc.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.databinding.FragmentPopularBinding

class PopularFragment : Fragment(R.layout.fragment_popular) {

    private val binding by viewBinding(FragmentPopularBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}