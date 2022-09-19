package frog.company.bonusapp.adapter

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import frog.company.bonusapp.R
import frog.company.bonusapp.helper.inter.IClientCard
import frog.company.bonusapp.model.Card


class AdapterStock(
    private val dataList : ArrayList<Int>,
    private val listner : IClientStock) : RecyclerView.Adapter<AdapterStock.ViewHolder>(){

    private lateinit var context : Context

    interface IClientStock{
        fun onResult()
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var imgStock : ImageView = view.findViewById(R.id.imgStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_stock,
                parent,
                false
            )
        )

        context = parent.context
        return v
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imgStock.setImageResource(dataList[position])
        holder.imgStock.setOnClickListener {
            listner.onResult()
        }
    }
}