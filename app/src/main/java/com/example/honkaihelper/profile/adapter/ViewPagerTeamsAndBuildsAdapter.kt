package com.example.honkaihelper.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.honkaihelper.databinding.ViewPagerProfileTeamsAndBuildsBinding
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.visible


class ViewPagerTeamsAndBuildsAdapter(
    private val actionListener: ViewPagerTeamsAndBuildsListener
) : BaseAdapter<ViewPagerProfileTeamsAndBuildsBinding, List<Any>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewPagerProfileTeamsAndBuildsBinding.inflate(layoutInflater, parent, false)

        return ViewPagerTeamsAndBuildsViewHolder(binding)
    }

    private inner class ViewPagerTeamsAndBuildsViewHolder(private val binding: ViewPagerProfileTeamsAndBuildsBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: List<Any>) {
            if (model.isEmpty()) {
                binding.viewStubEmpty.visible()
                binding.recyclerViewPagerProfile.gone()
            } else {
                if (adapterPosition == 0) {
                    val mAdapter = BuildsAdapter(object : BuildsAdapterListener {
                        override fun onBuildClick(idBuild: Int) {
                            actionListener.onBuildClick(idBuild)
                        }
                    })
                    mAdapter.list = model as List<BuildHeroWithUser>
                    binding.recyclerViewPagerProfile.apply {
                        layoutManager = LinearLayoutManager(this.context)
                        adapter = mAdapter
                    }
                } else {
                    val mAdapter = TeamsAdapter(object : TeamsAdapterListener {
                        override fun onTeamClick(idTeam: Int) {
                            actionListener.onTeamClick(idTeam)
                        }
                    })
                    mAdapter.list = model as List<TeamHero>
                    binding.recyclerViewPagerProfile.apply {
                        layoutManager = LinearLayoutManager(this.context)
                        adapter = mAdapter
                    }
                }
            }
        }
    }
}