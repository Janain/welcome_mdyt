package com.wangjj.app.base

import android.content.Context

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:28.
 * @Title 抽象出View的公共方法
 */
interface BaseView {
    /**
     * 显示正在加载view
     */
    fun showLoading()

    /**
     * 关闭正在加载view
     */
    fun hideLoading()

    /**
     * 显示提示
     * @param msg
     */
    fun showToast(msg: String)

    /**
     * 显示请求错误提示
     */
    fun showErr()

    /**
     * 获取上下文
     * @return 上下文
     */
    fun getContext(): Context
}
