package com.example.honkaihelper.base_build_hero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.databinding.ItemRelicBaseBuildHeroBinding
import com.example.honkaihelper.info_about_hero.data.model.Decoration
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load

class DecorationsAdapter : BaseAdapter<ItemRelicBaseBuildHeroBinding, Decoration>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRelicBaseBuildHeroBinding.inflate(layoutInflater, parent, false)

        return DecorationsViewHolder(binding)
    }

    private inner class DecorationsViewHolder(private val binding: ItemRelicBaseBuildHeroBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: Decoration) {
            binding.imageRelicBaseBuildHero.load(model.image)
            if (adapterPosition + 1 == list.size) binding.imageNextRelic.gone()
        }
    }
}