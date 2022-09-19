package frog.company.bonusapp.ui.stock

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.R
import frog.company.bonusapp.adapter.AdapterStock
import frog.company.bonusapp.databinding.FragmentSettingsBinding
import frog.company.bonusapp.databinding.FragmentStockBinding


class StockFragment : Fragment(), AdapterStock.IClientStock {

    private var _binding : FragmentStockBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockBinding.inflate(inflater, container, false)

        val array = arrayListOf(
            R.drawable.big1,R.drawable.big2,R.drawable.big3,R.drawable.big4,
            R.drawable.big5,R.drawable.big6,R.drawable.big7,R.drawable.big8,
            R.drawable.big9
        )

        binding.list.adapter = AdapterStock(array, this)

        return binding.root
    }

    override fun onResult() {
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.viewPager,
            StockCompanyFragment()
        ).addToBackStack(null).commit()
    }
}