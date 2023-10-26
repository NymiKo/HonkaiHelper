package com.example.honkaihelper.base_build_hero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.base_build_hero.data.model.Eidolon
import com.example.honkaihelper.databinding.ItemEidolonHeroBinding
import com.example.honkaihelper.utils.load

class EidolonsHeroAdapter: RecyclerView.Adapter<EidolonsHeroAdapter.EidolonsHeroViewHolder>() {

    var eidolonList: List<Eidolon> = emptyList()
        set(value) {
            field = value
            notifyItemRangeInserted(0, value.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EidolonsHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEidolonHeroBinding.inflate(layoutInflater, parent, false)

        return EidolonsHeroViewHolder(binding)
    }

    override fun getItemCount(): Int = eidolonList.size

    override fun onBindViewHolder(holder: EidolonsHeroViewHolder, position: Int) {
        val eidolon = eidolonList[position]
        holder.bind(eidolon)
    }

    class EidolonsHeroViewHolder(private val binding: ItemEidolonHeroBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(eidolon: Eidolon) {
            binding.apply {
                imageEidolon.load(eidolon.image)
                textTitleEidolon.text = eidolon.title
                textDescriptionAbility.text = eidolon.description
            }
        }
    }
}