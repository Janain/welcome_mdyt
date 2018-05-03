package com.wangjj.app.mvp.view

import com.wangjj.app.base.BaseView
import com.wangjj.app.net.response.LoginResponse

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:28.
 * @Title 绑定登录数据
 */
interface LoginView : BaseView {
    /**
     * 绑定登录数据
     */
    fun bindLoginData(loginResponse: LoginResponse)
}