package com.example.nazarenopathfinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nazarenopathfinder.databinding.PathItemCellBinding

class PathItemAdapter(
    private val pathItems: List<PathItem>,
    private val clickListener: PathItemClickListener
): RecyclerView.Adapter<PathItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = PathItemCellBinding.inflate(from, parent, false)
        return PathItemViewHolder(parent.context, binding, clickListener)
    }

    override fun onBindViewHolder(holder: PathItemViewHolder, position: Int) {
        holder.bindPathItem(pathItems[position])
    }

    override fun getItemCount(): Int = pathItems.size
}