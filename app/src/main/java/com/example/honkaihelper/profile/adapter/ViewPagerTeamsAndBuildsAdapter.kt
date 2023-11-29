package com.example.honkaihelper.profile.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.honkaihelper.databinding.ViewPagerProfileTeamsAndBuildsBinding
import com.example.honkaihelper.teams.data.model.TeamHero


class ViewPagerTeamsAndBuildsAdapter : BaseAdapter<ViewPagerProfileTeamsAndBuildsBinding, List<Any>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewPagerProfileTeamsAndBuildsBinding.inflate(layoutInflater, parent, false)

        return ViewPagerTeamsAndBuildsViewHolder(binding)
    }

    private inner class ViewPagerTeamsAndBuildsViewHolder(private val binding: ViewPagerProfileTeamsAndBuildsBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: List<Any>) {
            if (adapterPosition == 0) {
                val mAdapter = BuildsAdapter()
                mAdapter.list = model as List<BuildHeroWithUser>
                Log.e("BUILDS", mAdapter.list.toString())
                binding.recyclerViewPagerProfile.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = mAdapter
                }
            } else {
                val mAdapter = TeamsAdapter()
                mAdapter.list = model as List<TeamHero>
                binding.recyclerViewPagerProfile.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = mAdapter
                }
            }
        }
    }
}