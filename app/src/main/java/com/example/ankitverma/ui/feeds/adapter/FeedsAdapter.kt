package com.example.ankitverma.ui.feeds.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.ankitverma.databinding.CardFeedsBinding
import com.example.ankitverma.domain.modals.Feeds

class FeedsAdapter :
    ListAdapter<Feeds, FeedsAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CardFeedsBinding =
            CardFeedsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: CardFeedsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun bind(position: Int) {
            val item: Feeds = getItem(position)
            binding.apply {
                userImageView.load(item.userImage)
                userNameTextView.text = item.userName
                feedDescriptionTextView.text = String.format("\" %s \"", item.description)
                feedDescriptionTextView.visibility =
                    if (item.description.isBlank()) View.GONE else View.VISIBLE
                feedCommentsTextView.text = String.format("View all %d comments", item.likes)
                userInstagramNameTextView.text = item.userInstagram
                feedImageImageview.load(item.image)
                actionLikeTextview.text = String.format("%d likes", item.likes)
                actionCommentTextview.text = String.format("%d comments", item.likes)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    private class DiffCallback : DiffUtil.ItemCallback<Feeds>() {
        override fun areItemsTheSame(
            oldItem: Feeds,
            newItem: Feeds
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Feeds,
            newItem: Feeds
        ): Boolean =
            oldItem == newItem
    }
}