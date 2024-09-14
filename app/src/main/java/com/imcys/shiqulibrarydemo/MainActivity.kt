package com.imcys.shiqulibrarydemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.imcys.shiqulibrarydemo.adaprer.MainHomeFragmentAdapter
import com.imcys.shiqulibrarydemo.base.BaseActivity
import com.imcys.shiqulibrarydemo.databinding.ActivityMainBinding
import com.imcys.shiqulibrarydemo.ui.hearing.HearingFragment
import com.imcys.shiqulibrarydemo.ui.home.HomeFragment
import com.imcys.shiqulibrarydemo.ui.library.LibraryFragment
import com.imcys.shiqulibrarydemo.ui.mine.MineFragment


/**
 * 书库
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }

        initView()


    }

    private fun initView() {

        binding.apply {
            bottomNavigationView.itemIconTintList = null

            val fragments = listOf(
                HomeFragment.newInstance(),
                HearingFragment.newInstance(),
                LibraryFragment.newInstance(),
                MineFragment.newInstance()
            )
            val adapter = MainHomeFragmentAdapter(supportFragmentManager, lifecycle, fragments)

            mainViewPager2.adapter = adapter
            mainViewPager2.isUserInputEnabled = false
            bottomNavigationView.setOnItemSelectedListener {
                mainViewPager2.currentItem = it.order
                true
            }
        }

    }

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

}