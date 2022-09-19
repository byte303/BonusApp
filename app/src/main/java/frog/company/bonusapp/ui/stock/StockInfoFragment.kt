package frog.company.bonusapp.ui.stock

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentStockCompanyBinding
import frog.company.bonusapp.databinding.FragmentStockInfoBinding

class StockInfoFragment : Fragment() {

    private var _binding : FragmentStockInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockInfoBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnAddBonus.setOnClickListener{
            onDialogInfo()
        }
        return binding.root
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