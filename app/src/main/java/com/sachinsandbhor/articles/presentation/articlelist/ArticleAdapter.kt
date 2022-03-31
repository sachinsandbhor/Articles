package com.sachinsandbhor.articles.presentation.articlelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachinsandbhor.articles.R
import com.sachinsandbhor.articles.data.entity.ArticleEntity
import com.sachinsandbhor.articles.data.entity.ArticlesEntity
import com.sachinsandbhor.articles.presentation.util.DateUtil.Companion.formatDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

class ArticleAdapter: RecyclerView.Adapter<ArticleViewHolder>() {

    private val articleList = ArrayList<ArticleEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = articleList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = articleList.size

    fun updateArticles(articleList: ArticlesEntity) {
        this.articleList.clear()
        this.articleList.addAll(articleList.articles)
        notifyDataSetChanged()
    }
}

 class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(articleItem: ArticleEntity) {
        with(itemView) {
            news_description.text = articleItem.title
            Picasso.get().load(articleItem.urlToImage).placeholder(R.mipmap.ic_launcher).into(news_thumbnail)
            val publishDate = formatDate(articleItem.publishedAt!!)
            news_date.text = publishDate
        }
    }

}
