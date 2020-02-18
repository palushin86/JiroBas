package ru.palushin86.jirka.ui.cabinets

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cabinet.view.*
import ru.palushin86.jirka.R

class CabinetsViewHolder(
    view: View,
    listener: DeleteCabinetListener
) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.tv_cabinet_name)
    val owner: TextView = view.findViewById(R.id.tv_cabinet_owner)
    val level: TextView = view.findViewById(R.id.tv_cabinet_level)

    init {
        view.delete_cabinet.setOnClickListener { listener.delete(adapterPosition) }
    }

}