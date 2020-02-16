package ru.palushin86.jirka.ui.equipment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SpinnerAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_add_equipment.view.*
import kotlinx.android.synthetic.main.fragment_equipment.*
import ru.palushin86.jirka.R
import ru.palushin86.jirka.data.DataManager
import ru.palushin86.jirka.data.contracts.EquipmentDataManager
import ru.palushin86.jirka.entity.Equipment

class EquipmentFragment : Fragment(), DeleteEquipmentListener {
    private lateinit var homeViewModel: EquipmentViewModel
    private var dataManager: EquipmentDataManager = DataManager()
    private lateinit var adapter: EquipmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(EquipmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_equipment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_equipments.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = EquipmentAdapter(dataManager.equipments, this)
        rv_equipments.adapter = adapter
        add_equipment.setOnClickListener { onEquipmentTypeAddClick() }
    }

    override fun delete(position: Int) {
        if (!checkLinksExist()) {
            (dataManager.equipments as MutableList).removeAt(position)
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
            val dialogLayout = inflater.inflate(R.layout.dialog_add_equipment, null)
            val etEquipmentName  = dialogLayout.findViewById<EditText>(R.id.et_equipment_name)
            val etInventoryNumber  = dialogLayout.findViewById<EditText>(R.id.et_inventory_number)
            val etNumber  = dialogLayout.findViewById<EditText>(R.id.et_number)
            val etIp  = dialogLayout.findViewById<EditText>(R.id.et_ip)
            val etOs  = dialogLayout.findViewById<EditText>(R.id.et_os)

            val listOfTypes = dataManager.equipmentTypes
                .map { it.name }
            val spinnerTypeAdapter = activity?.let {
                ArrayAdapter<String>(it, R.layout.equipment_type_spinner, listOfTypes)
            } as SpinnerAdapter
            val spinnerType = dialogLayout.spinner_equipment_type
            spinnerType.adapter = null
            spinnerType.gravity = Gravity.CENTER
            spinnerType.adapter = spinnerTypeAdapter

            val listOfCabinets = dataManager.cabinets
                .map { it.name }
            val spinnerCabinetAdapter = activity?.let {
                ArrayAdapter<String>(it, R.layout.equipment_type_spinner, listOfCabinets)
            } as SpinnerAdapter
            val spinnerCabinet = dialogLayout.spinner_cabinet
            spinnerCabinet.adapter = null
            spinnerCabinet.gravity = Gravity.CENTER
            spinnerCabinet.adapter = spinnerCabinetAdapter

            AlertDialog.Builder(context)
                .setTitle("Ввод нового типа оборудования")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    createEquipmentType(
                        spinnerType.selectedItemPosition,
                        spinnerCabinet.selectedItemPosition,
                        etEquipmentName.text,
                        etInventoryNumber.text,
                        etNumber.text,
                        etIp.text,
                        etOs.text
                    ) }
                .setNegativeButton(android.R.string.no) { _, _ -> }
                .setView(dialogLayout)
                .create()
                .show()
        }

    }

    private fun createEquipmentType(
        type: Int,
        cabinet: Int,
        name: Editable?,
        inventory: Editable?,
        number: Editable?,
        ip: Editable?,
        os: Editable?
    ) {
        (dataManager.equipments as MutableList)
            .add(Equipment(
                equipmentTypeId = type,
                cabinetId = cabinet,
                name = name.toString(),
                inventoryNumber = inventory.toString(),
                number = number.toString().toInt(),
                ip = ip?.toString(),
                os = os?.toString()
            ))
        adapter.notifyDataSetChanged()
    }
}