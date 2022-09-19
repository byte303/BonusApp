package frog.company.bonusapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import frog.company.bonusapp.R
import frog.company.bonusapp.adapter.AdapterMyCard
import frog.company.bonusapp.adapter.AdapterNotActiveCard
import frog.company.bonusapp.databinding.FragmentAddCardBinding
import frog.company.bonusapp.databinding.FragmentHomeBinding
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.helper.inter.IClientCard
import frog.company.bonusapp.model.Card
import io.paperdb.Paper


class AddCardFragment : Fragment(), IClientCard {

    private var _binding : FragmentAddCardBinding? = null
    private val binding get() = _binding!!

    private var listArray = ArrayList<Card>()
    private var myCard = ArrayList<Card>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCardBinding.inflate(inflater, container, false)

        myCard = Paper.book().read(AppConst.PAPER_CARD, ArrayList())!!

        onLoad()
        binding.list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.list.adapter = AdapterNotActiveCard(listArray, this)

        return binding.root
    }

    private fun onLoad(){
        listArray.clear()
        for(i in myCard){
            if(i.status == 0)
                listArray.add(i)
        }
    }

    override fun onSelectCard(card: Card) {
        myCard = Paper.book().read(AppConst.PAPER_CARD, ArrayList())!!

        for(i in myCard)
            if(i.id == card.id)
                i.status = 1

        Toast.makeText(requireContext(), "Карта успешно добавлена!", Toast.LENGTH_SHORT).show()
        onLoad()
    }
}