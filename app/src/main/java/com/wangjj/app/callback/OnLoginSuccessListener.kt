package com.wangjj.app.callback

import com.wangjj.app.net.response.LoginResponse

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:43.
 * @Title 登录成功的回调函数
 */
interface OnLoginSuccessListener {

    /**
     * 登录成功的回调函数
     */
    fun loginSuccess(loginResponse: LoginResponse)

}