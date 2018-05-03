package com.wangjj.app.mvp.view

import com.wangjj.app.base.BaseView
import com.wangjj.app.net.response.LoginResponse

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:28.
 * @Title 绑定注册
 */
interface SignUpView : BaseView {
    /**
     * 绑定注册
     */
    fun bindSignUpData(loginResponse: LoginResponse)
}
