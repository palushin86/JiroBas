package ru.palushin86.jirka.ui.equipment_type

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.palushin86.jirka.R
import ru.palushin86.jirka.entity.EquipmentType

class EquipmentTypeAdapter(
    private val types: List<EquipmentType>,
    private val listener: DeleteEquipmentTypeListener
) : RecyclerView.Adapter<EquipmentTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentTypeViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_equipment_type, parent, false)
        return EquipmentTypeViewHolder(v, listener)
    }

    override fun getItemCount(): Int {
        return types.size
    }

    override fun onBindViewHolder(holder: EquipmentTypeViewHolder, position: Int) {
        holder.type.text = types[position].name
    }

}