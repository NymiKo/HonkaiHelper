package com.example.honkaihelper.builds_hero_from_users.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.R
import com.example.honkaihelper.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.honkaihelper.databinding.ItemBuildsHeroBinding
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.backgroundWeapon
import com.example.honkaihelper.utils.load

class BuildsHeroListAdapter(
    private val actionListener: BuildsHeroListListener
) : RecyclerView.Adapter<BuildsHeroListAdapter.BuildsHeroListViewHolder>(), OnClickListener {

    var buildsHeroList = emptyList<BuildHeroWithUser>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildsHeroListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBuildsHeroBinding.inflate(layoutInflater, parent, false)

        binding.cardBuildsHero.setOnClickListener(this)

        return BuildsHeroListViewHolder(binding)
    }

    override fun getItemCount(): Int = buildsHeroList.size

    override fun onBindViewHolder(holder: BuildsHeroListViewHolder, position: Int) {
        val buildHero = buildsHeroList[position]
        holder.bind(buildHero)
    }

    class BuildsHeroListViewHolder(private val binding: ItemBuildsHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(buildHero: BuildHeroWithUser) {
            binding.apply {
                imageHeroAvatarInBuildsHero.load(buildHero.hero.localAvatarPath)
                imageHeroAvatarInBuildsHero.backgroundHero(buildHero.hero.rarity)
                textHeroNameInBuildsHero.text = buildHero.hero.name
                imageHeroWeapon.load(buildHero.weapon.image)
                imageHeroWeapon.backgroundWeapon(buildHero.weapon.rarity)
                imageHeroRelicTwoParts.load(buildHero.relicTwoParts.image)
                imageHeroRelicFourParts.load(buildHero.relicFourParts.image)
                imageHeroDecoration.load(buildHero.decoration.image)
                textBuildFrom.text = textBuildFrom.context.getString(R.string.build_from, buildHero.buildUser.nickname)
                imageProfile.load(buildHero.buildUser.avatar)

                cardBuildsHero.tag = buildHero.idBuild
            }
        }
    }

    override fun onClick(view: View?) {
        val idBuild = view?.tag as Int
        actionListener.onClick(idBuild)
    }
}