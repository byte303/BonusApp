package frog.company.bonusapp.ui.main

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentActivatorCardBinding
import frog.company.bonusapp.databinding.FragmentInfoMyCardBinding
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.Card
import io.paperdb.Paper

class ActivatorCardFragment : Fragment() {
    private lateinit var card: Card

    private var _binding : FragmentActivatorCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            card = it.getSerializable(ARG_PARAM1) as Card
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentActivatorCardBinding.inflate(inflater, container, false)


        binding.txtName.text = card.name
        binding.cardColor.setCardBackgroundColor(resources.getColor(card.color))
        binding.imgLogo.setImageResource(card.image)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.btnNext.setOnClickListener {
            binding.btnNext.isEnabled = false
            val list = Paper.book().read(AppConst.PAPER_CARD, ArrayList<Card>())!!
            for(i in list)
                if(i.id == card.id)
                    i.status = 1

            Paper.book().write(AppConst.PAPER_CARD, list)

            onDialogInfo()
        }

        return binding.root
    }

    private fun onDialogInfo(){
        val factory = LayoutInflater.from(requireContext())
        val deleteDialogView: View = factory.inflate(R.layout.dialog_activate, null)
        val deleteDialog = AlertDialog.Builder(requireContext(), R.style.MyDialogTheme)
        deleteDialog.setView(deleteDialogView)

        deleteDialog
            .setCancelable(true)

        val del = deleteDialog.create()

        deleteDialog.show()
    }

    companion object {
        const val ARG_PARAM1 = "param1"
    }
}