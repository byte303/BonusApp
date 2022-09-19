package frog.company.bonusapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentEditPhoneBinding
import frog.company.bonusapp.databinding.FragmentRegistrationBinding
import frog.company.bonusapp.enums.EnumTypeCode
import frog.company.bonusapp.ui.registration.SendCodeFragment

class EditPhoneFragment : Fragment() {

    private var _binding : FragmentEditPhoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditPhoneBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener {
            val number = binding.edtPhone.text.toString()
            val fragment = SendCodeFragment().apply {
                arguments = Bundle().apply {
                    putString(SendCodeFragment.ARG_PARAM1, number)
                    putInt(SendCodeFragment.ARG_PARAM2, EnumTypeCode.NEW_PHONE.value)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                fragment
            ).addToBackStack(null).commit()
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return binding.root
    }
}