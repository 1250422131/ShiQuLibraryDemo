package com.imcys.shiqulibrarydemo.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class CommonViewHolder<VB:ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)