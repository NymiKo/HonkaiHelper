package com.example.honkaihelper.base_build_hero.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.databinding.ItemRelicBaseBuildHeroBinding
import com.example.honkaihelper.databinding.ItemWeaponBaseBuildHeroBinding
import com.example.honkaihelper.info_about_hero.data.model.Relic
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load

class RelicsAdapter(
    private val actionListener: ItemClickListener
): BaseAdapter<ItemRelicBaseBuildHeroBinding, Relic>(), OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRelicBaseBuildHeroBinding.inflate(layoutInflater, parent, false)

        binding.imageRelicBaseBuildHero.setOnClickListener(this)

        return RelicsViewHolder(binding)
    }

    private inner class RelicsViewHolder(private val binding: ItemRelicBaseBuildHeroBinding): BaseViewHolder(binding) {
        override fun bind(model: Relic) {
            binding.imageRelicBaseBuildHero.load(model.image)
            binding.imageRelicBaseBuildHero.tag = model.idRelic
            if (adapterPosition + 1 == list.size) binding.imageNextRelic.gone()
        }
    }

    override fun onClick(v: View?) {
        val itemId = v?.tag as Int
        actionListener.onItemClick(itemId)
    }
}