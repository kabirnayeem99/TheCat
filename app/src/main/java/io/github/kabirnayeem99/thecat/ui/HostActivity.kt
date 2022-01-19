package io.github.kabirnayeem99.thecat.ui

import dagger.hilt.android.AndroidEntryPoint
import io.github.kabirnayeem99.thecat.R
import io.github.kabirnayeem99.thecat.common.base.BaseActivity
import io.github.kabirnayeem99.thecat.databinding.ActivityHostBinding

@AndroidEntryPoint
class HostActivity : BaseActivity<ActivityHostBinding>() {

    override val layout: Int
        get() = R.layout.activity_host

    override fun onCreated() {
        super.onCreated()

    }
}