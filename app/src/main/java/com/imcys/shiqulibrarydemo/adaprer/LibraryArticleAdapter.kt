package com.imcys.shiqulibrarydemo.adaprer

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.imcys.shiqulibrarydemo.base.BaseRecyclerViewAdapter
import com.imcys.shiqulibrarydemo.base.CommonViewHolder
import com.imcys.shiqulibrarydemo.databinding.ItemLibraryArticleBinding
import com.imcys.shiqulibrarydemo.model.LibraryArticleLisData

class LibraryArticleAdapter : BaseRecyclerViewAdapter<ItemLibraryArticleBinding,LibraryArticleLisData.Item>() {

    var dataList = listOf<LibraryArticleLisData.Item>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonViewHolder<ItemLibraryArticleBinding> {
        val binding = ItemLibraryArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommonViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(
        holder: CommonViewHolder<ItemLibraryArticleBinding>,
        position: Int
    ) {

        val item = dataList[position]

        holder.binding.apply {
            Glide.with(articleCover).load(item.cover).into(articleCover)
            articleTitle.text = item.title
            articleLevel.text = "难度：${item.lexile}"
            articleWord.text = "${item.wordNum}词"

            main.setOnClickListener {

            }
        }

    }
}