package ru.palushin86.jirka.ui.cabinets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.palushin86.jirka.R
import ru.palushin86.jirka.entities.Cabinet

class CabinetsAdapter(
    private var cabinets: List<Cabinet>,
    private val listener: DeleteCabinetListener
) : RecyclerView.Adapter<CabinetsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CabinetsViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cabinet, parent, false)
        return CabinetsViewHolder(v, listener)
    }

    override fun getItemCount(): Int {
        return cabinets.size
    }

    override fun onBindViewHolder(holder: CabinetsViewHolder, position: Int) {
        holder.name.text = cabinets[position].name
        holder.owner.text = cabinets[position].owner
        holder.level.text = cabinets[position].level
    }

    fun setData(it: List<Cabinet>) {
        cabinets = it
        notifyDataSetChanged()
    }

}