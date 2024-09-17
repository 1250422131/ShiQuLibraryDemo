package com.imcys.shiqulibrarydemo.adaprer

import android.content.res.ColorStateList
import android.graphics.Color
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.imcys.shiqulibrarydemo.base.BaseRecyclerViewAdapter
import com.imcys.shiqulibrarydemo.base.CommonViewHolder
import com.imcys.shiqulibrarydemo.databinding.ItemLibraryArticleBinding
import com.imcys.shiqulibrarydemo.model.LibraryArticleLisData
import com.imcys.shiqulibrarydemo.model.eum.ArticleType

class LibraryArticleAdapter :
    ListAdapter<LibraryArticleLisData.Item, CommonViewHolder<ItemLibraryArticleBinding>>(object :
        DiffUtil.ItemCallback<LibraryArticleLisData.Item>() {
        override fun areItemsTheSame(
            oldItem: LibraryArticleLisData.Item,
            newItem: LibraryArticleLisData.Item
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LibraryArticleLisData.Item,
            newItem: LibraryArticleLisData.Item,
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }) {

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

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(
        holder: CommonViewHolder<ItemLibraryArticleBinding>,
        position: Int
    ) {

        val item = getItem(position)

        holder.binding.apply {
            Glide.with(articleCover).load(item.cover).into(articleCover)
            articleTitle.text = item.title
            articleLevel.text = "难度：${item.lexile}"
            articleWord.text = "${item.wordNum}词"

            articleType.text = item.type
            val color =
                Color.parseColor(
                    ArticleType.entries.firstOrNull { it.id == item.typeId }?.color
                        ?: "#f03752"
                )
            articleTypeLy.backgroundTintList = ColorStateList.valueOf(color)
            main.setOnClickListener {

            }
        }

    }
}