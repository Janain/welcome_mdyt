package com.wangjj.app.mvp.presenter

import android.content.Context
import com.wangjj.app.base.BasePresenter
import com.wangjj.app.callback.MvpCallback
import com.wangjj.app.mvp.moudle.SignUpMoudle
import com.wangjj.app.net.response.LoginResponse
import com.wangjj.app.mvp.view.SignUpView


/**
 * @Author Wangjj
 * @Create 2018/5/3  10:28.
 * @Title
 */
class SignUpPresenter : BasePresenter<SignUpView>() {
    var signUpView: SignUpView? = null
    var signUpMoudle: SignUpMoudle = SignUpMoudle()
    var activityContext: Context? = null

    override fun attachView(mvpView: SignUpView) {
        this.signUpView = mvpView
        activityContext = mvpView.getContext()
    }

    override fun detachView() {
        signUpView = null
    }

    override fun isViewAttached(): Boolean {
        return signUpView !== null
    }

    override fun getView(): SignUpView {
        return this.signUpView!!
    }

    fun signUpAccount(userName: String, passWord: String, rePassWord: String) {
        signUpView?.showLoading()
        signUpMoudle.signUpAccount(userName, passWord, rePassWord, object : MvpCallback<LoginResponse> {
            override fun onSuccess(data: LoginResponse) {
                signUpView?.bindSignUpData(data)
            }

            override fun onFailure(msg: String) {
                signUpView?.showToast(msg)
            }

            override fun onError() {
                signUpView?.showErr()
            }

            override fun onComplete() {
                signUpView?.hideLoading()
            }

            override fun showLoading() {
                signUpView?.showLoading()
            }

            override fun dissMissLoading() {
                signUpView?.hideLoading()
            }

        })
    }

}