package frog.company.bonusapp.ui.main

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentExchangeBinding
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.Card
import io.paperdb.Paper


class ExchangeFragment : Fragment() {
    private lateinit var card: Card

    private var listArray = ArrayList<Card>()
    private var myCard = ArrayList<Card>()
    private var arrayText = ArrayList<String>()
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            card = it.getSerializable(ARG_PARAM1) as Card
        }
    }

    private var _binding : FragmentExchangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExchangeBinding.inflate(inflater, container, false)

        myCard = Paper.book().read(AppConst.PAPER_CARD, ArrayList<Card>())!!

        for(i in myCard){
            if(i.status == 1) {
                listArray.add(i)
                arrayText.add(i.name)
            }
        }

        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_dropdown, arrayText)
        binding.cmbCard.setAdapter(arrayAdapter)
        arrayAdapter.filter.filter(null)
        binding.cmbCard.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            index = position
            binding.txtBalance.text = String.format("Всего баллов: " + listArray[index].balance)
        }

        binding.btnNext.setOnClickListener {
            try{
                if(binding.edtCount.text.toString() != ""){
                    val price = binding.edtCount.text.toString().toInt()
                    if(price <= listArray[index].balance){
                        for(i in myCard){
                            if(i.id == card.id){
                                i.balance += price
                            }
                            if(i.id == listArray[index].id){
                                i.balance -= price
                            }
                        }
                        Paper.book().write(AppConst.PAPER_CARD, myCard)
                        onDialogInfo()
                    }else
                        Toast.makeText(requireContext(), "У Вас нет такого количества баллов!", Toast.LENGTH_SHORT).show()
                }else
                    Toast.makeText(requireContext(), "Вы не ввели количество!", Toast.LENGTH_SHORT).show()
            }catch (ex : Exception){
                Toast.makeText(requireContext(), ex.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }

    private fun onDialogInfo(){
        val factory = LayoutInflater.from(requireContext())
        val deleteDialogView: View = factory.inflate(R.layout.dialog_exchange, null)
        val deleteDialog = AlertDialog.Builder(requireContext(), R.style.MyDialogTheme)
        deleteDialog.setView(deleteDialogView)

        deleteDialog.setCancelable(true)

        val del = deleteDialog.create()

        val btnNext = deleteDialogView.findViewById<Button>(R.id.btnNext)
        val dell = deleteDialog.show()
        btnNext.setOnClickListener {
            del.dismiss()
            dell.dismiss()

            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.viewPager,
                HomeFragment()
            ).commit()
        }

    }

    companion object {
        const val ARG_PARAM1 = "ARG_PARAM1"
    }
}