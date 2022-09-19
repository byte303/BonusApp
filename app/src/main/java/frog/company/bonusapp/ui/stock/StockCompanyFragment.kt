package frog.company.bonusapp.ui.stock

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.R
import frog.company.bonusapp.adapter.AdapterStock
import frog.company.bonusapp.databinding.FragmentSettingsBinding
import frog.company.bonusapp.databinding.FragmentStockCompanyBinding

class StockCompanyFragment : Fragment(), AdapterStock.IClientStock {
    private var _binding : FragmentStockCompanyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStockCompanyBinding.inflate(inflater, container, false)

        val array = arrayListOf(
            R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4,
            R.drawable.banner5,R.drawable.banner6,R.drawable.banner7,R.drawable.banner8)

        binding.list.adapter = AdapterStock(array, this)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnAddBonus.setOnClickListener{
            onDialogInfo()
        }
        return binding.root
    }

    override fun onResult() {
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.viewPager,
            StockInfoFragment()
        ).addToBackStack(null).commit()
    }

    private fun onDialogInfo(){
        val factory = LayoutInflater.from(requireContext())
        val deleteDialogView: View = factory.inflate(R.layout.dialog_add_bonus, null)
        val deleteDialog = AlertDialog.Builder(requireContext(), R.style.MyDialogTheme)
        deleteDialog.setView(deleteDialogView)

        deleteDialog
            .setCancelable(true)

        val del = deleteDialog.create()

        deleteDialog.show()
    }
}