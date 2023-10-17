package com.example.honkaihelper.create_build_hero.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.ItemCreateBuildEquipmentBinding
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.utils.backgroundEquipment
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.loadWithPlaceholder

class CreateBuildEquipmentAdapter(
    private val actionListener: CreateBuildEquipmentListener
): ListAdapter<Equipment, ViewHolder>(CreateBuildEquipmentDiffUtil()), OnClickListener {

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size) VIEW_TYPE_ADD_BUTTON else VIEW_TYPE_DATA
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCreateBuildEquipmentBinding.inflate(layoutInflater, parent, false)

        binding.imageHeroEquipment.setOnClickListener(this)
        binding.imageRemoveEquipment.setOnClickListener(this)

        return if (viewType == VIEW_TYPE_DATA) CreateBuildEquipmentViewHolder(binding) else AddEquipmentViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is CreateBuildEquipmentViewHolder -> {
                val equipment = currentList[position]
                holder.bind(equipment)
            }
            is AddEquipmentViewHolder -> {
                holder.bind()
            }
        }
    }

    class CreateBuildEquipmentViewHolder(private val binding: ItemCreateBuildEquipmentBinding): ViewHolder(binding.root) {
        fun bind(equipment: Equipment) {
            binding.imageHeroEquipment.load(equipment.image)
            binding.imageHeroEquipment.backgroundEquipment(equipment)
            binding.imageHeroEquipment.imageTintList = null

            binding.imageRemoveEquipment.tag = equipment.id
        }
    }

    class AddEquipmentViewHolder(private val binding: ItemCreateBuildEquipmentBinding): ViewHolder(binding.root) {
        fun bind() {
            binding.imageHeroEquipment.loadWithPlaceholder("", R.drawable.ic_add)
            binding.imageRemoveEquipment.gone()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.image_hero_equipment -> {
                actionListener.onAddEquipmentClick()
            }
            R.id.image_remove_equipment -> {
                val idEquipment = v.tag as Int
                actionListener.onRemoveEquipmentClick(idEquipment)
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_DATA = 0
        private const val VIEW_TYPE_ADD_BUTTON = 1
    }
}