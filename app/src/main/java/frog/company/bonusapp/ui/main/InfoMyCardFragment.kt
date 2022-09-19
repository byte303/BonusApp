package frog.company.bonusapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import frog.company.bonusapp.R
import frog.company.bonusapp.adapter.AdapterHistory
import frog.company.bonusapp.adapter.AdapterMyCard
import frog.company.bonusapp.databinding.FragmentInfoMyCardBinding
import frog.company.bonusapp.model.Card
import frog.company.bonusapp.ui.registration.SendCodeFragment

class InfoMyCardFragment : Fragment() {

    private var _binding : FragmentInfoMyCardBinding? = null
    private val binding get() = _binding!!

    private lateinit var card: Card

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
        _binding = FragmentInfoMyCardBinding.inflate(inflater, container, false)

        binding.txtName.text = card.name
        binding.txtBalance.text = card.balance.toString()
        binding.cardColor.setCardBackgroundColor(resources.getColor(card.color))
        binding.imgLogo.setImageResource(card.image)

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.list.adapter = AdapterHistory(card)

        binding.btnTransfer.setOnClickListener {
            val fragment = ExchangeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ExchangeFragment.ARG_PARAM1, card)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                fragment
            ).addToBackStack(null).commit()
        }

        binding.btnBuy.setOnClickListener {
            val fragment = BuyBonusFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(BuyBonusFragment.ARG_PARAM1, card)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                fragment
            ).addToBackStack(null).commit()
        }
        return binding.root
    }

    companion object {
        const val ARG_PARAM1 = "param1"
    }
}