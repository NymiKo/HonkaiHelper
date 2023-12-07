package com.example.tanorami.base_build_hero.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.example.tanorami.R
import com.example.tanorami.base.BaseAdapter
import com.example.tanorami.databinding.ItemRelicBaseBuildHeroBinding
import com.example.tanorami.info_about_hero.data.model.Relic
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.load

class RelicsAdapter(
    private val actionListener: ItemClickListener
) : BaseAdapter<ItemRelicBaseBuildHeroBinding, Relic>(), OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRelicBaseBuildHeroBinding.inflate(layoutInflater, parent, false)

        binding.imageRelicBaseBuildHero.setOnClickListener(this)

        return RelicsViewHolder(binding)
    }

    private inner class RelicsViewHolder(private val binding: ItemRelicBaseBuildHeroBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: Relic) {
            ViewCompat.setTransitionName(
                binding.imageRelicBaseBuildHero, binding.imageRelicBaseBuildHero.context.getString(
                    R.string.base_build_relic_transition_name, model.idRelic
                )
            )
            binding.imageRelicBaseBuildHero.load(model.image)
            binding.imageRelicBaseBuildHero.tag = model.idRelic
            if (adapterPosition + 1 == list.size) binding.imageNextRelic.gone()
        }
    }

    override fun onClick(v: View?) {
        val itemId = v?.tag as Int
        actionListener.onItemClick(itemId, v as ImageView)
    }
}