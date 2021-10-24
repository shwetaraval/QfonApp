package com.qfonapp.ui.activity

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.qfonapp.R
import com.qfonapp.databinding.ActivityMainBinding
import com.qfonapp.ui.adapter.PagerAdapter
import com.qfonapp.ui.fragment.HomeFragment
import com.qfonapp.ui.fragment.OtherFragment
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity(), HasAndroidInjector {
    private lateinit var binding: ActivityMainBinding
    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //make view full screen
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        initView()
    }

    private fun initView() {
        //remove default tinting from bottom navigation
        binding.contentMain.bottomView.itemIconTintList = null

        //add fragments to viewpager
        binding.contentMain.viewpager.offscreenPageLimit = 5
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment.newInstance(), "")
        adapter.addFragment(OtherFragment.newInstance(getString(R.string.second_tab)), "")
        adapter.addFragment(OtherFragment.newInstance(getString(R.string.third_tab)), "")
        adapter.addFragment(OtherFragment.newInstance(getString(R.string.forth_tab)), "")
        adapter.addFragment(OtherFragment.newInstance(getString(R.string.fifth_tab)), "")
        binding.contentMain.viewpager.adapter = adapter

        //update pager item on bottom navigation selected
        binding.contentMain.bottomView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_home -> binding.contentMain.viewpager.setCurrentItem(0, false)
                R.id.page_tab_two -> {
                    binding.contentMain.viewpager.setCurrentItem(1, false)
                }
                R.id.page_add -> {
                    binding.contentMain.viewpager.setCurrentItem(2, false)
                }
                R.id.page_video -> binding.contentMain.viewpager.setCurrentItem(3, false)
                R.id.page_profile -> binding.contentMain.viewpager.setCurrentItem(4, false)
            }
            false
        }


        //update navigation item on page change selected
        binding.contentMain.viewpager?.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) prevMenuItem!!.isChecked = false
                else binding.contentMain.bottomView?.menu?.getItem(0)?.isChecked = false
                binding.contentMain.bottomView?.menu?.getItem(position)?.isChecked = true
                prevMenuItem = binding.contentMain.bottomView?.menu?.getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        binding.contentMain.bottomView.selectedItemId = R.id.page_home

        //set drawer
        var actionBarDrawerToggle =
            ActionBarDrawerToggle(this, binding.drawer, R.string.nav_open, R.string.nav_close)

        binding.drawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.contentMain.toolbarMain.ivMenu.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.START)
        }
        binding.drawerMain.ivClose.setOnClickListener {
            binding.drawer.closeDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START))
            binding.drawer.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win: Window = activity.window
        val winParams: WindowManager.LayoutParams = win.getAttributes()
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}