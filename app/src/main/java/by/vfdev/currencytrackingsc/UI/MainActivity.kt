package by.vfdev.currencytrackingsc.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import by.vfdev.currencytrackingsc.R
import by.vfdev.currencytrackingsc.ViewModel.MainViewModel
import by.vfdev.currencytrackingsc.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainVM: MainViewModel by viewModels()
    private val binding by viewBinding(ActivityMainBinding::bind)

    private val navController by lazy {
        supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        )!!.findNavController()
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(setOf(
                R.id.popularFragment,
                R.id.favoritesFragment)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setupWithNavController(navController)

        val currency = resources.getStringArray(R.array.currency)

        val adapterPopular = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, currency)
        binding.spinner.adapter = adapterPopular

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View,
                position: Int, id: Long) {
                mainVM.selectCurrency.value = currency[position]
                mainVM.getListCurrency(mainVM.selectCurrency.value.toString())
                mainVM.getListFavoriteCurrency(mainVM.selectCurrency.value.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id != R.id.popularFragment) navController.popBackStack()
        else super.onBackPressed()
    }
}