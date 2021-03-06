package com.wangjj.app.fragment

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.wangjj.app.R
import com.wangjj.app.base.BaseFragment
import com.wangjj.app.callback.OnSignUpSuccessListener
import com.wangjj.app.customwidget.WithdrawClearEditText
import com.wangjj.app.net.response.LoginResponse
import com.wangjj.app.mvp.presenter.SignUpPresenter
import com.wangjj.app.utils.InPutTypeUtil
import com.wangjj.app.mvp.view.SignUpView

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:30.
 * @Title
 */
class SignUpFragment : BaseFragment(), SignUpView {

    var account: WithdrawClearEditText? = null
    var password: WithdrawClearEditText? = null
    var rePassword: WithdrawClearEditText? = null
    var signUp: TextView? = null
    var signUpPresenter: SignUpPresenter? = null
    var accountText: String = ""
    var passwordText: String = ""
    var rePasswordText: String = ""
    var onSignUpSuccessListener: OnSignUpSuccessListener? = null


    override fun setFragmentLayout(): Int {
        return R.layout.fragment_sign_up
    }

    override fun initData() {
        signUpPresenter = SignUpPresenter()
        signUpPresenter?.attachView(this)
    }

    override fun initEvent() {
        signUp?.setOnClickListener {
            InPutTypeUtil.hidenputKeyboardImmediately(activityContext)
            if (localCheck()) {
                accountText = account!!.text.toString()
                passwordText = password!!.text.toString()
                rePasswordText = rePassword!!.text.toString()
                signUpPresenter?.signUpAccount(accountText, passwordText, rePasswordText)
            }
        }
    }

    override fun initView(rootView: View) {
        account = rootView.findViewById(R.id.wce_account)
        password = rootView.findViewById(R.id.wce_pass_word)
        rePassword = rootView.findViewById(R.id.wce_pass_word_confirm)
        signUp = rootView.findViewById(R.id.tv_sign_up)
    }

    override fun showLoading() {

    }

    override fun bindSignUpData(loginResponse: LoginResponse) {
        when (loginResponse.errorCode) {
            0 -> {
                onSignUpSuccessListener?.signUpSuccess()
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
        if (TextUtils.isEmpty(rePassword?.text)) {
            Toast.makeText(activityContext, "确认密码不能为空", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!(password!!.text.toString().equals(rePassword!!.text.toString()))) {
            Toast.makeText(activityContext, "两次密码不一致", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password!!.text.length < 6) {
            Toast.makeText(activityContext, "密码长度最少为6位", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun setSignUpListener(onSignUpSuccessListener: OnSignUpSuccessListener) {
        this.onSignUpSuccessListener = onSignUpSuccessListener
    }

}