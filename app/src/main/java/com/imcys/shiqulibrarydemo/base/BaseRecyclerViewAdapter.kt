package com.imcys.shiqulibrarydemo.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.imcys.shiqulibrarydemo.adaprer.CommonViewHolder
import com.imcys.shiqulibrarydemo.model.ArticleTypeData


abstract class BaseRecyclerViewAdapter<VB:ViewBinding> : RecyclerView.Adapter<CommonViewHolder<VB>>() {
    protected var onItemClick: ((ArticleTypeData) -> Unit)? = null
}