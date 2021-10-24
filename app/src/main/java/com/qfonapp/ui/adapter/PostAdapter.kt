package com.qfonapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.qfonapp.data.model.PostModel
import com.qfonapp.databinding.RowPostBinding
import com.qfonapp.utils.isNotEmpty
import com.qfonapp.utils.openVideoPlayer


class PostAdapter(
    private val context: Context?,
) :
    ListAdapter<PostModel, PostAdapter.PostViewHolder>(StringAnsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            RowPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(
            context, getItem(position)
        )
    }

    inner class PostViewHolder(var binding: RowPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            context: Context?,
            item: PostModel,
        ) =
            with(binding) {
                post = item
                /*
                Commenting below code because "height": 310 "width": 174 doesn't look good
                if (isNotEmpty(item.height) && isNotEmpty(item.width)) {
                    ivPostImage?.ratio = (item.height.toFloat() / item.width.toFloat())
                }*/
                ivPlayVideo?.setOnClickListener {
                    openVideoPlayer(context, item.content)
                }
            }
    }

    class StringAnsDiffCallback() : DiffUtil.ItemCallback<PostModel>() {
        override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem == newItem
        }
    }
}