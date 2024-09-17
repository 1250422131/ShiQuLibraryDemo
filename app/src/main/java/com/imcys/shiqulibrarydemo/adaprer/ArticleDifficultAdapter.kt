package com.imcys.shiqulibrarydemo.adaprer

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import com.imcys.shiqulibrarydemo.R
import com.imcys.shiqulibrarydemo.base.BaseRecyclerViewAdapter
import com.imcys.shiqulibrarydemo.base.CommonViewHolder
import com.imcys.shiqulibrarydemo.databinding.ItemLibraryArticleDifficultBinding


class ArticleDifficultAdapter : BaseRecyclerViewAdapter<ItemLibraryArticleDifficultBinding,Int>() {

    var dataList = listOf<Int>()

    var selectIndex = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonViewHolder<ItemLibraryArticleDifficultBinding> {
        val binding = ItemLibraryArticleDifficultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommonViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(
        holder: CommonViewHolder<ItemLibraryArticleDifficultBinding>,
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
                level.setTextColor(context.getColor(R.color.black))
            }

            level.text = data.toString()

            main.setOnClickListener {
                val tempIndex = selectIndex
                selectIndex = mPosition
                notifyItemChanged(tempIndex)
                notifyItemChanged(selectIndex)

                // 回调
                onItemClick?.invoke(data)
            }


        }
    }
}