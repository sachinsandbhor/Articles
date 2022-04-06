package com.sachinsandbhor.articles.presentation.articlelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sachinsandbhor.articles.BR
import com.sachinsandbhor.articles.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var activityMainBinding: ActivityMainBinding
    private val articleAdapter: ArticleAdapter by lazy { ArticleAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setBinding(viewModel)
        setRecyclerView()
        setObservers()
        viewModel.getTopArticles()
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = articleAdapter
    }

    private fun setBinding(viewModel: MainViewModel) {
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.setVariable(BR.viewModel, viewModel)
        activityMainBinding.executePendingBindings()
    }

    private fun setObservers() {
        viewModel.topArticles.observe(this) {
            if (it.articles.isNotEmpty()) {
                articleAdapter.updateArticles(it)
            }
        }
    }
}