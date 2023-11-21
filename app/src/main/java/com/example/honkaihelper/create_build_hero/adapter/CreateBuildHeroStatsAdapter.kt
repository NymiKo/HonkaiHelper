package com.example.honkaihelper.create_build_hero.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.databinding.ItemStatsEquipmentCreateBuildBinding
import com.example.honkaihelper.utils.load

class CreateBuildHeroStatsAdapter(
    private val actionListener: CreateBuildHeroStatsListener
) : BaseAdapter<ItemStatsEquipmentCreateBuildBinding, Int>(), OnItemSelectedListener {
    private val iconList = listOf(
        R.drawable.relic_piece_body,
        R.drawable.relic_piece_legs,
        R.drawable.relic_piece_sphere,
        R.drawable.relic_piece_rope
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStatsEquipmentCreateBuildBinding.inflate(layoutInflater, parent, false)

        binding.spinnerStatsCreateBuild.onItemSelectedListener = this

        return CreateBuildHeroStatsViewHolder(binding)
    }

    override fun getItemCount(): Int = 4

    private inner class CreateBuildHeroStatsViewHolder(private val binding: ItemStatsEquipmentCreateBuildBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: Int) {
            binding.imageStatsEquipment.load(iconList[adapterPosition])
            ArrayAdapter.createFromResource(
                binding.spinnerStatsCreateBuild.context,
                model,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerStatsCreateBuild.adapter = adapter
            }

            binding.spinnerStatsCreateBuild.tag = adapterPosition
        }
    }

    override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, selectedItemPosition: Int, id: Long) {
        val adapterPosition = parentView?.tag as Int
        actionListener.onSpinnerItemSelected(adapterPosition, selectedItemPosition)
    }

    override fun onNothingSelected(parentView: AdapterView<*>?) {
        val adapterPosition = parentView?.tag as Int
        actionListener.onSpinnerItemSelected(adapterPosition, 0)
    }
}