package com.york.android.redditpost.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.york.android.redditpost.R
import com.york.android.redditpost.api.PostEntity
import com.york.android.redditpost.databinding.ItemBottomLeftTitleBackgroundImageBinding
import com.york.android.redditpost.databinding.ItemLoadingBinding
import com.york.android.redditpost.databinding.ItemTitleImageApartBinding
import com.york.android.redditpost.viewmodel.PostListViewModel
import java.lang.IllegalArgumentException

class PostListAdapter(
    private val viewModel: PostListViewModel,
    diffCallback: PostDiffUtilCallback
) : PagedListAdapter<PostEntity, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_title_image_apart ->
                TitleImageApartViewHolder(ItemTitleImageApartBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ))
            R.layout.item_loading ->
                LoadingViewHolder(ItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ))
            R.layout.item_bottom_left_title_background_image ->
                BottomLeftTitleViewHolder(ItemBottomLeftTitleBackgroundImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ))
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + FOOTER_COUNT
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            itemCount != 1 && position < itemCount - 1 ->{
                if (position % 4 == 3) {
                    R.layout.item_bottom_left_title_background_image
                } else {
                    R.layout.item_title_image_apart
                }
            }
            else -> {
                R.layout.item_loading
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val posts = currentList ?: return
        if (posts.size == 0) return

        when (holder) {
            is TitleImageApartViewHolder -> {
                holder.binding.apply {
                    post = getItem(position)
                    viewModel = this@PostListAdapter.viewModel
                }
            }
            is BottomLeftTitleViewHolder -> {
                holder.binding.apply {
                    post = getItem(position)
                    viewModel = this@PostListAdapter.viewModel
                }
            }
        }
    }

    companion object {
        private const val FOOTER_COUNT = 1
    }
}

class PostDiffUtilCallback : DiffUtil.ItemCallback<PostEntity>() {
    override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem == newItem
    }
}

class TitleImageApartViewHolder(val binding: ItemTitleImageApartBinding) : RecyclerView.ViewHolder(binding.root)

class BottomLeftTitleViewHolder(val binding: ItemBottomLeftTitleBackgroundImageBinding)
    : RecyclerView.ViewHolder(binding.root)

class LoadingViewHolder(binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root)