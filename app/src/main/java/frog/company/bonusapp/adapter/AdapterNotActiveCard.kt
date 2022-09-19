package frog.company.bonusapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import frog.company.bonusapp.R
import frog.company.bonusapp.helper.inter.IClientCard
import frog.company.bonusapp.model.Card


class AdapterNotActiveCard(
    private val dataList : ArrayList<Card>,
    private val listener : IClientCard
) : RecyclerView.Adapter<AdapterNotActiveCard.ViewHolder>() {

    private lateinit var context : Context

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var cardBackground : CardView = view.findViewById(R.id.cardBackground)
        var imgLogo : ImageView = view.findViewById(R.id.imgLogo)
        var txtFullName : TextView = view.findViewById(R.id.txtFullName)
        var txtName : TextView = view.findViewById(R.id.txtName)
        var linear : CardView = view.findViewById(R.id.linear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_not_my_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = String.format(dataList[position].name + " Group")
        holder.txtFullName.text = String.format(dataList[position].name)
        holder.cardBackground.setCardBackgroundColor(context.resources.getColor(dataList[position].color))
        holder.imgLogo.setImageResource(dataList[position].image)

        holder.linear.setOnClickListener {
            listener.onSelectCard(dataList[position])
        }
    }
}