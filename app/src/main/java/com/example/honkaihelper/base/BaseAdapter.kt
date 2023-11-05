package com.example.honkaihelper.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<VB : ViewBinding, M> :
    RecyclerView.Adapter<BaseAdapter<VB, M>.BaseViewHolder>() {

    var list: List<M> = emptyList()
        set(value) {
            field = value
            notifyItemRangeInserted(0, value.size)
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    abstract inner class BaseViewHolder(private val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(model: M)
    }
}