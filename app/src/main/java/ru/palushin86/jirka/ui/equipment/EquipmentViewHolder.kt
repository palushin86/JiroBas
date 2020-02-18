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
    val name: TextView = view.findViewById(R.id.tv_equipment_name)
    val equipmentType: TextView = view.findViewById(R.id.tv_equipment_equipment_type)
    val number: TextView = view.findViewById(R.id.tv_equipment_number)
    val inventory_number: TextView = view.findViewById(R.id.tv_equipment_inventory_number)
    val ip: TextView = view.findViewById(R.id.tv_equipment_ip)
    val os: TextView = view.findViewById(R.id.tv_equipment_os)

    init {
        view.delete_equipment.setOnClickListener { listener.delete(adapterPosition) }
    }

}