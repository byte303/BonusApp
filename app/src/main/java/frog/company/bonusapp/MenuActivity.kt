package frog.company.bonusapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import frog.company.bonusapp.databinding.ActivityMenuBinding
import frog.company.bonusapp.ui.main.HomeFragment
import frog.company.bonusapp.ui.settings.SettingsFragment
import frog.company.bonusapp.ui.stock.StockFragment
import io.paperdb.Paper

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    private lateinit var navView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Paper.init(this)

        navView = binding.navView
        navView.setOnNavigationItemSelectedListener(navListener)

        navView.selectedItemId = R.id.navigation_home
        supportFragmentManager.beginTransaction().replace(
            R.id.viewPager,
            HomeFragment()
        ).commit()
    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {item ->
            var selectedFragment: Fragment? = null

            if(binding.navView.selectedItemId != item.itemId){
                when (item.itemId) {
                    R.id.navigation_home -> selectedFragment = HomeFragment()
                    R.id.navigation_settings -> selectedFragment = SettingsFragment()
                    R.id.navigation_wallet -> selectedFragment = StockFragment()
                }
                if(selectedFragment != null)
                    supportFragmentManager.beginTransaction().replace(
                        R.id.viewPager,
                        selectedFragment
                    ).commit()
                true
            }else
                false
        }
}