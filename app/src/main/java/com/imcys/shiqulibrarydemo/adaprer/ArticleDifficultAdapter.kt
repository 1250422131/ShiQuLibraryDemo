package com.imcys.shiqulibrarydemo.adaprer

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import com.imcys.shiqulibrarydemo.R
import com.imcys.shiqulibrarydemo.base.BaseRecyclerViewAdapter
import com.imcys.shiqulibrarydemo.base.CommonViewHolder
import com.imcys.shiqulibrarydemo.databinding.ItemArticleDifficultBinding


class ArticleDifficultAdapter : BaseRecyclerViewAdapter<ItemArticleDifficultBinding>() {

    var dataList = listOf<Int>()

    var selectIndex = 0
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonViewHolder<ItemArticleDifficultBinding> {
        val binding = ItemArticleDifficultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommonViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(
        holder: CommonViewHolder<ItemArticleDifficultBinding>,
        position: Int
    ) {
        val context = holder.itemView.context
        val data = dataList[position]
        val mPosition = holder.adapterPosition
        holder.binding.apply {
            if (mPosition == selectIndex) {
                main.setBackgroundResource(R.drawable.shape_article_difficult_round_bg)
                level.setTextColor(context.getColor(R.color.primary))
            } else {
                // 清除背景
                main.setBackgroundResource(0)
                val typedValue = TypedValue()
                context.theme.resolveAttribute(android.R.attr.textColorPrimary, typedValue, true)
                level.setTextColor(typedValue.data)
            }
        }
    }
}