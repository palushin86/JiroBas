package ru.palushin86.jirka.ui.cabinets

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_cabinet.*
import ru.palushin86.jirka.R
import ru.palushin86.jirka.entities.Cabinet
import ru.palushin86.jirka.entities.Equipment

class CabinetsFragment : Fragment(), DeleteCabinetListener {
    private lateinit var viewModel: CabinetsViewModel
    private lateinit var adapter: CabinetsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(this).get(CabinetsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_cabinet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_cabinets.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = CabinetsAdapter(emptyList(), this)
        rv_cabinets.adapter = adapter

        viewModel.getCabinets().observe(this, Observer {
            viewModel.cabinets = it as MutableList<Cabinet>
            adapter.setData(it)
        })

        add_cabinet.setOnClickListener { onEquipmentTypeAddClick() }
    }

    override fun delete(position: Int) {
        checkLinksExist(position).observe(this, Observer { equipments ->
            if (equipments.isEmpty()) {
                val deletedItem = viewModel.cabinets[position]
                viewModel.deleteCabinet(deletedItem)
                adapter.notifyDataSetChanged()
            } else {
                val linkEquipments = equipments.joinToString(separator = ", ") { it.name }
                Toast.makeText(context, "Удалить нельзя т.к. кабинет используется в записях имущества $linkEquipments", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun checkLinksExist(position: Int): LiveData<List<Equipment>> {
        val item = viewModel.cabinets[position]
        return viewModel.getEquipmentsByCabinet(item)
    }

    private fun onEquipmentTypeAddClick() {
        val inflater = activity?.layoutInflater

        if (inflater != null) {
            val dialogLayout = inflater.inflate(R.layout.dialog_add_cabinet, null)
            val etCabinet  = dialogLayout.findViewById<EditText>(R.id.et_cabinet_name)
            val etOwner  = dialogLayout.findViewById<EditText>(R.id.et_cabinet_owner)
            val etLevel  = dialogLayout.findViewById<EditText>(R.id.et_cabinet_level)
            AlertDialog.Builder(context)
                .setTitle("Ввод нового типа оборудования")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    createCabinet(
                        etCabinet.text.toString(),
                        etOwner.text.toString(),
                        etLevel.text.toString()
                    ) }
                .setNegativeButton(android.R.string.no) { _, _ -> }
                .setView(dialogLayout)
                .create()
                .show()
        }

    }

    private fun createCabinet(name: String, owner: String, level: String) {
        val addedItem = Cabinet(
            name = name,
            owner = owner,
            level = level
        )
        viewModel.insertCabinet(addedItem)
        adapter.notifyDataSetChanged()
    }
}