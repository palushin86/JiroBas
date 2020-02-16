package ru.palushin86.jirka.ui.equipment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.palushin86.jirka.R
import ru.palushin86.jirka.entity.Equipment

class EquipmentAdapter(
    private val equipments: List<Equipment>,
    private val listener: DeleteEquipmentListener
) : RecyclerView.Adapter<EquipmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equipment, parent, false)
        return EquipmentViewHolder(v, listener)
    }

    override fun getItemCount(): Int {
        return equipments.size
    }

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        holder.equipment.text = equipments[position].name
    }

}