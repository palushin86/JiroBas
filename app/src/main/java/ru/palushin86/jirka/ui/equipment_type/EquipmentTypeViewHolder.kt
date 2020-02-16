package ru.palushin86.jirka.ui.equipment_type

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_equipment_type.view.*
import ru.palushin86.jirka.R

class EquipmentTypeViewHolder(
    view: View,
    listener: DeleteEquipmentTypeListener
) : RecyclerView.ViewHolder(view) {
    val type: TextView = view.findViewById(R.id.tv_equipment_type)

    init {
        view.delete_equipment_type.setOnClickListener { listener.delete(adapterPosition) }
    }

}