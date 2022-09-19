package frog.company.bonusapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentBuyBonusBinding
import frog.company.bonusapp.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {

    private var _binding : FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener {
            Toast.makeText(requireContext(), "Оплата произошла успешно!", Toast.LENGTH_SHORT).show()

            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                HomeFragment()
            ).commit()
        }

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }
}