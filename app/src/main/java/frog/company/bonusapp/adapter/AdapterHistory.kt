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


class AdapterHistory(
    private val dataList : Card) : RecyclerView.Adapter<AdapterHistory.ViewHolder>(){

    private lateinit var context : Context

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var cardBackground : CardView = view.findViewById(R.id.cardBackground)
        var imgLogo : ImageView = view.findViewById(R.id.imgLogo)
        var txtName : TextView = view.findViewById(R.id.txtName)
        var txtTime : TextView = view.findViewById(R.id.txtTime)
        var txtPrice : TextView = view.findViewById(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_history,
                parent,
                false
            )
        )

        context = parent.context
        return v
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txtName.text = dataList.name
        holder.txtTime.text = String.format((0..22).random().toString() + ":" + (1..59).random().toString())
        holder.txtPrice.text = String.format("+ " + (150..1900).random().toString())

        holder.cardBackground.setCardBackgroundColor(context.resources.getColor(dataList.color))
        holder.imgLogo.setImageResource(dataList.image)
    }
}