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


class AdapterMyCard(
    private val dataList : ArrayList<Card>,
    private val listener : IClientCard) : RecyclerView.Adapter<AdapterMyCard.ViewHolder>(){

    private lateinit var context : Context

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var cardBackground : CardView = view.findViewById(R.id.cardBackground)
        var imgLogo : ImageView = view.findViewById(R.id.imgLogo)
        var txtBalance : TextView = view.findViewById(R.id.txtBalance)
        var imgMore : ImageView = view.findViewById(R.id.imgMore)
        var linear : ConstraintLayout = view.findViewById(R.id.linear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_home,
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

        holder.txtBalance.text = dataList[position].balance.toString()
        holder.cardBackground.setCardBackgroundColor(context.resources.getColor(dataList[position].color))
        holder.imgLogo.setImageResource(dataList[position].image)

        holder.imgMore.setOnClickListener {
            val popup = PopupMenu(context, holder.imgMore)
            popup.inflate(R.menu.menu_my_card)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_edit ->
                        true
                    R.id.action_delete ->
                        true
                    R.id.action_share ->
                        true
                    else -> false
                }
            }
            popup.show()
        }

        holder.linear.setOnClickListener {
            listener.onSelectCard(dataList[position])
        }
    }

}