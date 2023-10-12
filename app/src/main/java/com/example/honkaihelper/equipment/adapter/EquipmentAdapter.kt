package com.example.honkaihelper.equipment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.databinding.ItemEquipmentBinding
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.utils.load

class EquipmentAdapter: RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder>() {

    var mEquipmentList = emptyList<Equipment>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEquipmentBinding.inflate(layoutInflater, parent, false)
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
        }
    }
}