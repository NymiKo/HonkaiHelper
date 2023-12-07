package com.example.tanorami.equipment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tanorami.databinding.ItemEquipmentBinding
import com.example.tanorami.equipment.data.model.Equipment
import com.example.tanorami.utils.backgroundEquipment
import com.example.tanorami.utils.load

class EquipmentAdapter(
    private val actionListener: EquipmentListener
): RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder>(), OnClickListener {

    var mEquipmentList = emptyList<Equipment>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEquipmentBinding.inflate(layoutInflater, parent, false)

        binding.imageEquipment.setOnClickListener(this)

        return EquipmentViewHolder(binding)
    }

    override fun getItemCount(): Int = mEquipmentList.size

    override fun onBindViewHolder(holder: EquipmentViewHolder, position: Int) {
        val equipment = mEquipmentList[position]
        holder.bind(equipment)
    }

    class EquipmentViewHolder(private val binding: ItemEquipmentBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(equipment: Equipment) {
            binding.imageEquipment.load(equipment.image)
            binding.imageEquipment.backgroundEquipment(equipment)

            binding.imageEquipment.tag = equipment
        }
    }

    override fun onClick(v: View?) {
        val equipment = v?.tag as Equipment
        actionListener.onClick(equipment)
    }
}