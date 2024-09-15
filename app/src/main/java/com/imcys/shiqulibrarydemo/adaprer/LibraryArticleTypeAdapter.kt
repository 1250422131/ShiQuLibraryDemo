package com.imcys.shiqulibrarydemo.adaprer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imcys.shiqulibrarydemo.R
import com.imcys.shiqulibrarydemo.base.BaseRecyclerViewAdapter
import com.imcys.shiqulibrarydemo.databinding.ItemLibraryArticleTypeBinding
import com.imcys.shiqulibrarydemo.model.ArticleTypeData

class LibraryArticleTypeAdapter : BaseRecyclerViewAdapter<ItemLibraryArticleTypeBinding>() {

    var dataList = listOf<ArticleTypeData>()

    var selectIndex = 0
        private set


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommonViewHolder<ItemLibraryArticleTypeBinding> {
        val binding = ItemLibraryArticleTypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CommonViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CommonViewHolder<ItemLibraryArticleTypeBinding>,
        position: Int
    ) {
        val context = holder.itemView.context
        holder.binding.apply {
            if (position == selectIndex) {
                typeName.setTextColor(context.getColor(R.color.primary))
                main.setBackgroundColor(context.getColor(R.color.white))
            } else {
                typeName.setTextColor(context.getColor(R.color.black))
                main.setBackgroundColor(context.getColor(R.color.neutral))
            }

            typeName.text = dataList[position].type
            indicator.visibility = if (position == selectIndex) View.VISIBLE else View.GONE

            main.setOnClickListener {

                // 更新选中状态
                val mPosition = holder.adapterPosition
                val tempIndex = selectIndex
                selectIndex = mPosition

                notifyItemChanged(tempIndex)
                notifyItemChanged(selectIndex)

                // 回调
                onItemClick?.invoke(dataList[position])
            }
        }
    }

    override fun getItemCount(): Int = dataList.size


}