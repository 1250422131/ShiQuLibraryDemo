package com.imcys.shiqulibrarydemo.adaprer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imcys.shiqulibrarydemo.R
import com.imcys.shiqulibrarydemo.base.BaseRecyclerViewAdapter
import com.imcys.shiqulibrarydemo.base.CommonViewHolder
import com.imcys.shiqulibrarydemo.databinding.ItemLibraryArticleTypeBinding
import com.imcys.shiqulibrarydemo.model.ArticleTypeData

class LibraryArticleTypeAdapter : BaseRecyclerViewAdapter<ItemLibraryArticleTypeBinding,ArticleTypeData>() {

    var dataList = listOf<ArticleTypeData>()
        set(value) {
            field = value.toMutableList().apply {
                add(0, ArticleTypeData(id = 0, type = "精选阅读"))
            }.toList()
        }

    private var selectIndex = 0


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
        val data = dataList[position]
        holder.binding.apply {
            if (position == selectIndex) {
                typeName.setTextColor(context.getColor(R.color.primary))
                main.setBackgroundColor(context.getColor(R.color.white))
            } else {
                typeName.setTextColor(context.getColor(R.color.black))
                main.setBackgroundColor(context.getColor(R.color.neutral))
            }

            typeName.text = data.type
            indicator.visibility = if (position == selectIndex) View.VISIBLE else View.GONE

            if (holder.adapterPosition == 0) {
                // 精心挑选 设置drawableStart
                typeName.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_carefully_selected,
                    0,
                    0,
                    0
                )
            } else {
                typeName.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    0,
                    0
                )
            }

            main.setOnClickListener {

                // 更新选中状态
                val mPosition = holder.adapterPosition
                val tempIndex = selectIndex
                selectIndex = mPosition

                notifyItemChanged(tempIndex)
                notifyItemChanged(selectIndex)

                // 回调
                onItemClick?.invoke(data)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size


}