package com.imcys.shiqulibrarydemo.ui.hearing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imcys.shiqulibrarydemo.R

/**
 * 听力
 */
class HearingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hearing, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HearingFragment()
    }
}