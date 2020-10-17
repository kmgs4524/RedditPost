package com.york.android.redditpost.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.york.android.redditpost.PostListViewModel
import com.york.android.redditpost.R
import com.york.android.redditpost.ViewModelFactory
import com.york.android.redditpost.databinding.ActivityPostListBinding

class PostListActivity : AppCompatActivity() {

    private lateinit var viewModel: PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =DataBindingUtil.setContentView<ActivityPostListBinding>(
            this,
            R.layout.activity_post_list
        )
        val postListAdapter = PostListAdapter(PostDiffUtilCallback())
        binding.posts.apply {
            layoutManager = LinearLayoutManager(
                this@PostListActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(DividerItemDecoration(this@PostListActivity, RecyclerView.VERTICAL))
            this.adapter = postListAdapter
        }

        viewModel = ViewModelFactory(application).create(PostListViewModel::class.java)

        viewModel.posts.observe(this, Observer {
            postListAdapter.submitList(it)
        })
    }

    companion object {
        const val TAG = "PostListActivity"
    }
}