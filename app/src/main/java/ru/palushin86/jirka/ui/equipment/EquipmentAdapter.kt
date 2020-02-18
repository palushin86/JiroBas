package ru.palushin86.jirka.ui.equipment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.palushin86.jirka.R
import ru.palushin86.jirka.entities.Equipment

class EquipmentAdapter(
    private var equipments: List<Equipment>,
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
        holder.name.text = equipments[position].name
        holder.equipmentType.text = equipments[position].equipmentTypeId.toString()
        holder.number.text = equipments[position].number.toString()
        holder.inventory_number.text = equipments[position].inventoryNumber
        holder.ip.text = equipments[position].ip
        holder.os.text = equipments[position].os
    }

    fun setData(it: List<Equipment>) {
        equipments = it
        notifyDataSetChanged()
    }

}