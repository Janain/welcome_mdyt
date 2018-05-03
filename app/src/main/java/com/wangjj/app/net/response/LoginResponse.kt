package com.wangjj.app.net.response

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:38.
 * @Title
 */
data class LoginResponse(var errorCode: Int,
                         var errorMsg: String?,
                         var data: Data) {
    data class Data(var id: Int,
                    var username: String,
                    var password: String,
                    var icon: String?,
                    var type: Int,
                    var collectIds: List<Int>?)
}