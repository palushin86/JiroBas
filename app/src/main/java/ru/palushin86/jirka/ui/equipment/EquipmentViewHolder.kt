package ru.palushin86.jirka.ui.equipment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_equipment.view.*
import ru.palushin86.jirka.R

class EquipmentViewHolder(
    view: View,
    listener: DeleteEquipmentListener
) : RecyclerView.ViewHolder(view) {
    val equipment: TextView = view.findViewById(R.id.tv_equipment)

    init {
        view.delete_equipment.setOnClickListener { listener.delete(adapterPosition) }
    }

}