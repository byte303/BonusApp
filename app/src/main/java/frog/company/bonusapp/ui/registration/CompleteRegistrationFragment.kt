package frog.company.bonusapp.ui.registration

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.MenuActivity
import frog.company.bonusapp.databinding.FragmentCompleteRegistrationBinding
import frog.company.bonusapp.enums.EnumStatusUser
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.User
import io.paperdb.Paper

class CompleteRegistrationFragment : Fragment() {

    private var _binding : FragmentCompleteRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompleteRegistrationBinding.inflate(inflater, container, false)

        binding.btnDone.setOnClickListener {
            val user = Paper.book().read<User>(AppConst.PAPER_DATA, User())!!
            user.status = EnumStatusUser.ACTIVE.value
            Paper.book().write(AppConst.PAPER_DATA, user)

            startActivity(Intent(requireActivity(), MenuActivity::class.java))
            requireActivity().finish()
        }

        return binding.root
    }
}