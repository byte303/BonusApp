package frog.company.bonusapp.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentSetNameBinding
import frog.company.bonusapp.enums.EnumTypePin
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.User
import io.paperdb.Paper


class SetNameFragment : Fragment() {

    private var _binding : FragmentSetNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetNameBinding.inflate(inflater, container, false)

        binding.btnNext.setOnClickListener {

            val user = Paper.book().read<User>(AppConst.PAPER_DATA, User())!!
            user.name = binding.edtName.text.toString()
            Paper.book().write(AppConst.PAPER_DATA, user)

            val fragment = SetPinCodeFragment.newInstance(EnumTypePin.CREATE.value)
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                fragment
            ).commit()
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }
}