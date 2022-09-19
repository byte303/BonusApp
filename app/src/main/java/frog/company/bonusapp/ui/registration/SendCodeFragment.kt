package frog.company.bonusapp.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentSendCodeBinding
import frog.company.bonusapp.enums.EnumStatusUser
import frog.company.bonusapp.enums.EnumTypeCode
import frog.company.bonusapp.enums.EnumTypePin
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.User
import frog.company.bonusapp.ui.profile.ProfileFragment
import io.paperdb.Paper

class SendCodeFragment : Fragment() {

    private var _binding : FragmentSendCodeBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var type: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            type = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSendCodeBinding.inflate(inflater, container, false)

        binding.txtNumberPhone.text = String.format("+$param1")

        binding.pinVIew.setOtpCompletionListener { otp ->
            val pin = otp

            if(EnumTypeCode.LOGIN.value == type){
                val fragment = SetPinCodeFragment.newInstance(EnumTypePin.CREATE.value)
                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.viewPager,
                    fragment
                ).commit()
            }else if(EnumTypeCode.NEW_PHONE.value == type){

                val user = Paper.book().read(AppConst.PAPER_DATA, User())!!
                user.phone = param1!!
                Paper.book().write(AppConst.PAPER_DATA, user)

                Toast.makeText(requireContext(), "Ваш номер успешно изменен", Toast.LENGTH_SHORT).show()

                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.viewPager,
                    ProfileFragment()
                ).commit()
            }else{
                val user = User(
                    "",
                    param1!!,
                    EnumStatusUser.NOT_ACTIVE.value
                )
                Paper.book().write(AppConst.PAPER_DATA, user)

                requireActivity().supportFragmentManager.beginTransaction().replace(
                    R.id.viewPager,
                    SetNameFragment()
                ).addToBackStack(null).commit()
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }

    companion object {
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"
    }
}