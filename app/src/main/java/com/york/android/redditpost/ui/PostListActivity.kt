package com.york.android.redditpost.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.york.android.redditpost.viewmodel.PostListViewModel
import com.york.android.redditpost.R
import com.york.android.redditpost.databinding.ActivityPostListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PostListActivity : AppCompatActivity() {

    private val viewModel: PostListViewModel by viewModel()

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

        viewModel.posts.observe(this, Observer {
            postListAdapter.submitList(it)
        })
    }

    companion object {
        const val TAG = "PostListActivity"
    }
}