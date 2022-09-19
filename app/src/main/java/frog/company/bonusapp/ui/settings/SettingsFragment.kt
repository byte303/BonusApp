package frog.company.bonusapp.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.MainActivity
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentEditNameBinding
import frog.company.bonusapp.databinding.FragmentSettingsBinding
import frog.company.bonusapp.enums.EnumTypePin
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.User
import frog.company.bonusapp.ui.registration.SetPinCodeFragment
import io.paperdb.Paper


class SettingsFragment : Fragment() {

    private var _binding : FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.btnLogOut.setOnClickListener {
            Paper.book().delete(AppConst.PAPER_DATA)
            Paper.book().delete(AppConst.PAPER_CARD)

            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

        binding.btnPassword.setOnClickListener {
            val fragment = SetPinCodeFragment.newInstance(EnumTypePin.NEW_PIN.value)
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                fragment
            ).addToBackStack(null).commit()
        }

        return binding.root
    }
}