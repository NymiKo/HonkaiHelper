package com.example.honkaihelper.builds_hero.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.R
import com.example.honkaihelper.builds_hero.data.model.BuildHero
import com.example.honkaihelper.databinding.ItemBuildsHeroBinding
import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.utils.backgroundEquipment
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.load

class BuildsHeroListAdapter :
    RecyclerView.Adapter<BuildsHeroListAdapter.BuildsHeroListViewHolder>() {

    var buildsHeroList = emptyList<BuildHero>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildsHeroListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBuildsHeroBinding.inflate(layoutInflater, parent, false)

        return BuildsHeroListViewHolder(binding)
    }

    override fun getItemCount(): Int = buildsHeroList.size

    override fun onBindViewHolder(holder: BuildsHeroListViewHolder, position: Int) {
        val buildHero = buildsHeroList[position]
        holder.bind(buildHero)
    }

    class BuildsHeroListViewHolder(private val binding: ItemBuildsHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(buildHero: BuildHero) {
            binding.apply {
                imageHeroAvatarInBuildsHero.backgroundHero(buildHero.hero)
                textHeroNameInBuildsHero.text = buildHero.hero.name
                imageHeroWeapon.backgroundEquipment(buildHero.weapon)
                imageHeroRelic.backgroundEquipment(buildHero.relic)
                imageHeroDecoration.backgroundEquipment(buildHero.decoration)
                textBuildFrom.text = textBuildFrom.context.getString(R.string.build_from, buildHero.user.login)
                imageProfile.load(buildHero.user.avatarUrl)
            }
        }
    }
}