package com.wangjj.app.fragment

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.wan.constant.Constant
import com.wangjj.app.R
import com.wangjj.app.base.BaseFragment
import com.wangjj.app.base.BasePreference
import com.wangjj.app.callback.OnLoginSuccessListener
import com.wangjj.app.customwidget.WithdrawClearEditText
import com.wangjj.app.net.response.LoginResponse
import com.wangjj.app.mvp.presenter.LoginPresenter
import com.wangjj.app.utils.InPutTypeUtil
import com.wangjj.app.mvp.view.LoginView

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:28.
 * @Title
 */
class LoginFragment : BaseFragment(), LoginView {

    var loginPresenter: LoginPresenter? = null
    var account: WithdrawClearEditText? = null
    var password: WithdrawClearEditText? = null
    var login: TextView? = null
    var onLoginSuccessListener: OnLoginSuccessListener? = null


    //检查登陆
    private var isLogin: Boolean by BasePreference(Constant.LOGIN_KEY, false)
    //本地用户名
    private var user: String by BasePreference(Constant.USERNAME_KEY, "")
    //本地密码
    private var pwd: String by BasePreference(Constant.PASSWORD_KEY, "")

    override fun setFragmentLayout(): Int {
        return R.layout.fragment_login
    }

    override fun initData() {
        loginPresenter = LoginPresenter()
        loginPresenter?.attachView(this)
    }

    override fun initEvent() {
        login?.setOnClickListener {
            InPutTypeUtil.hidenputKeyboardImmediately(activityContext)
            if (localCheck()) {
                loginPresenter?.loginAccount(account?.text.toString(), password?.text.toString())
            }
        }
    }

    override fun initView(rootView: View) {
        account = rootView.findViewById(R.id.wce_account)
        password = rootView.findViewById(R.id.wce_pass_word)
        login = rootView.findViewById(R.id.tv_login)
    }
    override fun showLoading() {

    }
    override fun bindLoginData(loginResponse: LoginResponse) {
        when (loginResponse.errorCode) {
            0 -> {
                onLoginSuccessListener?.loginSuccess(loginResponse)
            }
            else -> {
                Toast.makeText(activityContext, loginResponse.errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun hideLoading() {
    }

    override fun showToast(msg: String) {
        Toast.makeText(activityContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showErr() {
        Toast.makeText(activityContext, "请求错误", Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context {
        return activityContext!!
    }

    fun localCheck(): Boolean {
        if (TextUtils.isEmpty(account?.text)) {
            Toast.makeText(activityContext, "账号不能为空", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(password?.text)) {
            Toast.makeText(activityContext, "密码不能为空", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun setLoginSuccessListener(onLoginSuccessListener: OnLoginSuccessListener) {
        this.onLoginSuccessListener = onLoginSuccessListener
    }

}