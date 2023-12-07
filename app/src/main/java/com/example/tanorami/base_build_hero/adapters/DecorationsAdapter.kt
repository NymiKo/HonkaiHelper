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
import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.load

class DecorationsAdapter(
    private val actionListener: ItemClickListener
) : BaseAdapter<ItemRelicBaseBuildHeroBinding, Decoration>(), OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRelicBaseBuildHeroBinding.inflate(layoutInflater, parent, false)

        binding.imageRelicBaseBuildHero.setOnClickListener(this)

        return DecorationsViewHolder(binding)
    }

    private inner class DecorationsViewHolder(private val binding: ItemRelicBaseBuildHeroBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: Decoration) {
            ViewCompat.setTransitionName(
                binding.imageRelicBaseBuildHero, binding.imageRelicBaseBuildHero.context.getString(
                    R.string.base_build_decoration_transition_name, model.idDecoration
                )
            )
            binding.imageRelicBaseBuildHero.load(model.image)
            binding.imageRelicBaseBuildHero.tag = model.idDecoration
            if (adapterPosition + 1 == list.size) binding.imageNextRelic.gone()
        }
    }

    override fun onClick(v: View?) {
        val itemId = v?.tag as Int
        actionListener.onItemClick(itemId, v as ImageView)
    }
}