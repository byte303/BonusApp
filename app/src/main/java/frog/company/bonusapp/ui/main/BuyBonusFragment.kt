package frog.company.bonusapp.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import frog.company.bonusapp.R
import frog.company.bonusapp.databinding.FragmentBuyBonusBinding
import frog.company.bonusapp.databinding.FragmentExchangeBinding
import frog.company.bonusapp.helper.AppConst
import frog.company.bonusapp.model.Card
import io.paperdb.Paper

class BuyBonusFragment : Fragment() {
    private var _binding : FragmentBuyBonusBinding? = null
    private val binding get() = _binding!!
    private lateinit var card: Card

    private var listArray = ArrayList<Card>()
    private var myCard = ArrayList<Card>()
    private var arrayText = ArrayList<String>()
    private var index = 0
    private var total = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            card = it.getSerializable(ExchangeFragment.ARG_PARAM1) as Card
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBonusBinding.inflate(inflater, container, false)

        myCard = Paper.book().read(AppConst.PAPER_CARD, ArrayList())!!

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
        }

        binding.edtCount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(binding.edtCount.text.toString() != ""){
                    try {
                        total = binding.edtCount.text.toString().trim().toInt()
                        val text = String.format((total * 3).toString()).trim()
                        binding.txtPrice.text = String.format("$text руб.")
                    }catch (ex : Exception){
                        Toast.makeText(requireContext(), "Неверно введено число!", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    total = 0
                    binding.txtPrice.text = String.format("0 руб.")
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        binding.btnNext.setOnClickListener {
            try{
                if(binding.edtCount.text.toString() != ""){
                    val price = binding.edtCount.text.toString().toInt()
                    for(i in myCard){
                        if(i.id == listArray[index].id){
                            i.balance += price
                        }
                    }
                    Paper.book().write(AppConst.PAPER_CARD, myCard)

                    requireActivity().supportFragmentManager.beginTransaction().replace(
                        R.id.viewPager,
                        PaymentFragment()
                    ).addToBackStack(null).commit()
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

    companion object {
        const val ARG_PARAM1 = "ARG_PARAM1"
    }
}