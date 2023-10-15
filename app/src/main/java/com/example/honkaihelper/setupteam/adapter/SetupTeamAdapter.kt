package com.example.honkaihelper.setupteam.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.ItemSetupTeamBinding
import com.example.honkaihelper.setupteam.data.model.SetupHero
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.loadWithPlaceholder

class SetupTeamAdapter(
    private val weaponActionListener: SetupTeamListener
) : ListAdapter<SetupHero, SetupTeamAdapter.SetupTeamViewHolder>(DiffCallback()), OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetupTeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSetupTeamBinding.inflate(layoutInflater, parent, false)

        binding.imageHeroWeapon.setOnClickListener(this)
        binding.imageHeroRelic.setOnClickListener(this)
        binding.imageHeroDecoration.setOnClickListener(this)

        return SetupTeamViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: SetupTeamViewHolder, position: Int) {
        val setupHero = currentList[position]
        holder.bind(setupHero)
    }

    class SetupTeamViewHolder(private val binding: ItemSetupTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(setupHero: SetupHero) {
            binding.apply {
                imageHeroAvatarInSetupTeam.load(setupHero.hero.avatar)
                textHeroNameInSetupTeam.text = setupHero.hero.name
                backgroundHero(setupHero.hero.rarity)

                spinnerHeroLevel.setSelection(setupHero.level ?: 0)

                imageHeroWeapon.loadWithPlaceholder(setupHero.weapon?.image, R.drawable.ic_add)
                if (setupHero.weapon != null) imageHeroWeapon.imageTintList = null

                imageHeroWeapon.tag = setupHero
                imageHeroRelic.tag = setupHero
                imageHeroDecoration.tag = setupHero
                spinnerHeroLevel.tag = setupHero
            }
        }

        private fun backgroundHero(rarity: Boolean) {
            if (rarity) {
                binding.imageHeroAvatarInSetupTeam.background = ContextCompat.getDrawable(
                    binding.imageHeroAvatarInSetupTeam.context,
                    R.color.orange
                )
            } else {
                binding.imageHeroAvatarInSetupTeam.background = ContextCompat.getDrawable(
                    binding.imageHeroAvatarInSetupTeam.context,
                    R.color.violet
                )
            }
        }
    }

    override fun onClick(v: View?) {
        val setupHero = v?.tag as SetupHero
        val idItem = currentList.indexOf(setupHero)

        when (v.id) {
            R.id.image_hero_weapon -> {
                weaponActionListener.onWeaponClick(setupHero.hero.path, idItem)
            }

            R.id.image_hero_relic -> weaponActionListener.onRelicClick(idItem)
            R.id.image_hero_decoration -> weaponActionListener.onDecorationClick(idItem)
        }
    }
}