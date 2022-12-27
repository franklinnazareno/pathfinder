package com.example.nazarenopathfinder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.nazarenopathfinder.databinding.PathItemCellBinding

class PathItemViewHolder(
    private val context: Context,
    private val binding: PathItemCellBinding,
    private val clickListener: PathItemClickListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindPathItem(pathItem: PathItem) {
            binding.name.text = pathItem.name

            binding.pathCellContainer.setOnClickListener{
                clickListener.editPathItem(pathItem)
            }
        }
}