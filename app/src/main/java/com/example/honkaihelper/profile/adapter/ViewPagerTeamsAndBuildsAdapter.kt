package com.example.honkaihelper.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.databinding.ViewPagerProfileTeamsAndBuildsBinding


class ViewPagerTeamsAndBuildsAdapter : BaseAdapter<ViewPagerProfileTeamsAndBuildsBinding, List<Any>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewPagerProfileTeamsAndBuildsBinding.inflate(layoutInflater, parent, false)

        return ViewPagerTeamsAndBuildsViewHolder(binding)
    }

    private inner class ViewPagerTeamsAndBuildsViewHolder(private val binding: ViewPagerProfileTeamsAndBuildsBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: List<Any>) {
            if (adapterPosition == 0)
        }
    }
}