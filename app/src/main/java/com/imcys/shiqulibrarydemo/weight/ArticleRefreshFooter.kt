package com.imcys.shiqulibrarydemo.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.imcys.shiqulibrarydemo.databinding.RefreshArticleFooterBinding
import com.scwang.smart.refresh.layout.api.RefreshComponent
import com.scwang.smart.refresh.layout.api.RefreshFooter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.simple.SimpleComponent

class ArticleRefreshFooter : SimpleComponent, RefreshFooter {

    private var binding: RefreshArticleFooterBinding

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        binding = RefreshArticleFooterBinding.inflate(LayoutInflater.from(context))
        mWrappedView = binding.root
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        binding.apply {
            when (newState) {
                RefreshState.PullDownToRefresh -> {
                }



                RefreshState.ReleaseToRefresh -> {
                }

                RefreshState.Loading -> {
                    animationView.playAnimation()
                    animationView.repeatCount = 100
                }

                RefreshState.LoadFinish -> {
                    animationView.cancelAnimation()
                }

                else -> {}
            }
        }
    }
}

