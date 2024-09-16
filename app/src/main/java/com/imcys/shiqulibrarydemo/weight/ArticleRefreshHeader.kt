package com.imcys.shiqulibrarydemo.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.imcys.shiqulibrarydemo.databinding.RefreshArticleHeaderBinding
import com.scwang.smart.refresh.layout.api.RefreshComponent
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.simple.SimpleComponent

class ArticleRefreshHeader : SimpleComponent, RefreshHeader {

    private  var refreshArticleHeaderBinding: RefreshArticleHeaderBinding

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        refreshArticleHeaderBinding =
            RefreshArticleHeaderBinding.inflate(LayoutInflater.from(context))
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {

        refreshArticleHeaderBinding.apply {
            when (newState) {
                RefreshState.PullDownToRefresh -> {

                }

                RefreshState.PullDownCanceled -> {}
                RefreshState.ReleaseToRefresh -> {}
                RefreshState.Refreshing -> {
                    animationView.playAnimation()
                    animationView.repeatCount = 100
                }

                else -> {

                }
            }
        }


    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        return super.onFinish(refreshLayout, success)
    }

}