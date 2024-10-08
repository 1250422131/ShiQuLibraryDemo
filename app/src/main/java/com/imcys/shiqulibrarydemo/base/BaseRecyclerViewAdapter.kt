package com.imcys.shiqulibrarydemo.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.imcys.shiqulibrarydemo.model.ArticleTypeData


abstract class BaseRecyclerViewAdapter<VB:ViewBinding,D> : RecyclerView.Adapter<CommonViewHolder<VB>>() {
    var onItemClick: ((D) -> Unit)? = null
}