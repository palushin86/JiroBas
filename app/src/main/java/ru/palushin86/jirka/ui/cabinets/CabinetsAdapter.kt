package ru.palushin86.jirka.ui.cabinets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.palushin86.jirka.R
import ru.palushin86.jirka.entity.Cabinet
import ru.palushin86.jirka.entity.Equipment

class CabinetsAdapter(
    private val cabinets: List<Cabinet>,
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
        holder.cabinet.text = cabinets[position].name
    }

}