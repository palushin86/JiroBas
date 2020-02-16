package ru.palushin86.jirka.ui.cabinets

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_cabinet.*
import ru.palushin86.jirka.R
import ru.palushin86.jirka.data.DataManager
import ru.palushin86.jirka.data.contracts.CabinetsDataManager
import ru.palushin86.jirka.entity.Cabinet

class CabinetsFragment : Fragment(), DeleteCabinetListener {
    private lateinit var cabinetsViewModel: CabinetsViewModel
    private var dataManager: CabinetsDataManager = DataManager()
    private lateinit var adapter: CabinetsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cabinetsViewModel =
            ViewModelProviders.of(this).get(CabinetsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_cabinet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_cabinets.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = CabinetsAdapter(dataManager.cabinets, this)
        rv_cabinets.adapter = adapter

        add_cabinet.setOnClickListener { onEquipmentTypeAddClick() }
    }

    override fun delete(position: Int) {
        if (!checkLinksExist()) {
            (dataManager.cabinets as MutableList).removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }

    private fun checkLinksExist(): Boolean {
        //TODO: здесь перед удалением будет проверяться есть ли у оборудования связанные записи
        return false
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
                    createEquipmentType(
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

    private fun createEquipmentType(name: String, owner: String, level: String) {
        (dataManager.cabinets as MutableList)
            .add(
                Cabinet(
                    name = name,
                    owner = owner,
                    level = level
                )
            )
        adapter.notifyDataSetChanged()
    }
}