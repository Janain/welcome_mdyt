package com.wangjj.app.base

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.afollestad.aesthetic.Aesthetic
import com.afollestad.aesthetic.BottomNavBgMode
import com.afollestad.aesthetic.BottomNavIconTextMode
import com.gyf.barlibrary.ImmersionBar

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:12.
 * @Title 抽象出activity的公共方法
 */
abstract class BaseActivity : AppCompatActivity() {
    protected var mImmersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar!!.init()
        initData()
        setContentView(setContentLayoutId())
        initView()
        initEvent()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar!!.destroy()
    }

    protected abstract fun initData()

    protected abstract fun initEvent()

    protected abstract fun setContentLayoutId(): Int

    protected abstract fun initView()

    /**
     * 修改主体颜色
     */
    protected fun applyTheme(color: Int) {
        val covertColor = ContextCompat.getColor(this, color)
        Aesthetic.get()
                .colorPrimary(covertColor)
                .colorAccent(covertColor)
                .textColorSecondaryInverse(Color.WHITE)
                .colorStatusBarAuto()
                .colorNavigationBarAuto()
                .bottomNavigationBackgroundMode(BottomNavBgMode.PRIMARY_DARK)
                .bottomNavigationIconTextMode(BottomNavIconTextMode.BLACK_WHITE_AUTO)
                .apply()
        applyStatusBarColor(color)
    }

    /**
     * 修改状态栏颜色
     */
    protected fun applyStatusBarColor(color: Int) {
        mImmersionBar!!.statusBarColor(color)
                .statusBarAlpha(0.2F)
                .init()
    }

}
