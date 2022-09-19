package frog.company.bonusapp.ui.main

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import frog.company.bonusapp.R
import frog.company.bonusapp.adapter.AdapterMyCard
import frog.company.bonusapp.adapter.AdapterNotActiveCard
import frog.company.bonusapp.databinding.FragmentHomeBinding
import frog.company.bonusapp.enums.EnumTypeCode
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.helper.inter.IClientCard
import frog.company.bonusapp.model.Card
import frog.company.bonusapp.ui.profile.ProfileFragment
import frog.company.bonusapp.ui.registration.SendCodeFragment
import io.paperdb.Paper

class HomeFragment : Fragment(), IClientCard {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var listArray = ArrayList<Card>()
    private var myCard = ArrayList<Card>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        myCard = Paper.book().read(AppConst.PAPER_CARD, ArrayList())!!
        if(myCard.size == 0){
            listArray = Card().onLoad()
            myCard.addAll(listArray)

            for(i in 0..3){
                myCard[i].apply {
                    status = 1
                    balance = (0 until 15000).random().toDouble()
                }
            }
            Paper.book().write(AppConst.PAPER_CARD, myCard)
        }
        onLoad(true)
        binding.list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.list.adapter = AdapterMyCard(listArray, this)

        registerForContextMenu(binding.list)

        binding.rbtObmen.isChecked = true
        binding.rbtObmen.setOnCheckedChangeListener { _, b ->
            if(b) {
                onLoad(true)
                binding.list.adapter = AdapterMyCard(listArray, this)
            }
        }

        binding.rbtNotActive.setOnCheckedChangeListener { _, b ->
            if(b) {
                onLoad(false)
                binding.list.adapter = AdapterNotActiveCard(listArray, this)
            }
        }

        binding.btnFilter.setOnClickListener{
            val popup = PopupMenu(context, binding.btnFilter)
            popup.inflate(R.menu.menu_filter)
            popup.show()
        }

        binding.imgUser.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                ProfileFragment()
            ).addToBackStack(null).commit()
        }

        binding.imgAdd.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                AddCardFragment()
            ).addToBackStack(null).commit()
        }
        return binding.root
    }

    private fun onLoad(b : Boolean){
        listArray.clear()
        if(b){
            for(i in myCard){
                if(i.status == 1)
                    listArray.add(i)
            }
        }else{
            for(i in myCard){
                if(i.status == 0)
                    listArray.add(i)
            }
        }
    }

    override fun onSelectCard(card: Card) {
        if(card.status == 1){
            val fragment = InfoMyCardFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(InfoMyCardFragment.ARG_PARAM1, card)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                fragment
            ).addToBackStack(null).commit()
        }else{
            val fragment = ActivatorCardFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ActivatorCardFragment.ARG_PARAM1, card)
                }
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                fragment
            ).addToBackStack(null).commit()
        }
    }
}