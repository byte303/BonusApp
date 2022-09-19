package frog.company.bonusapp.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentAuthorizationBinding
import frog.company.bonusapp.enums.EnumTypeCode
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.User
import io.paperdb.Paper

class AuthorizationFragment : Fragment() {

    private var _binding : FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                RegistrationFragment()
            ).commit()
        }

        binding.btnNext.setOnClickListener {
            val number = binding.edtPhone.text.toString()

            val user = Paper.book().read<User>(AppConst.PAPER_DATA, User())!!
            user.status = 1
            user.phone = number
            user.name = "Имя Фамилия"
            Paper.book().write(AppConst.PAPER_DATA, user)

            val fragment = SendCodeFragment().apply {
                arguments = Bundle().apply {
                    putString(SendCodeFragment.ARG_PARAM1, number)
                    putInt(SendCodeFragment.ARG_PARAM2, EnumTypeCode.LOGIN.value)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                fragment
            ).addToBackStack(null).commit()
        }

        return binding.root
    }
}