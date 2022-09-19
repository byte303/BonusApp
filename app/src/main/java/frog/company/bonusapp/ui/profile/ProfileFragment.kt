package frog.company.bonusapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentProfileBinding
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.User
import io.paperdb.Paper


class ProfileFragment : Fragment() {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val user = Paper.book().read(AppConst.PAPER_DATA, User())!!

        binding.txtName.text = user.name
        binding.txtPhone.text = user.phone

        binding.btnEditName.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                EditNameFragment()
            ).addToBackStack(null).commit()
        }

        binding.btnEditPhone.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                EditPhoneFragment()
            ).addToBackStack(null).commit()
        }

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }
}