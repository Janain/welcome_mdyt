package com.wangjj.app.mvp.presenter

import android.content.Context
import com.wangjj.app.base.BasePresenter
import com.wangjj.app.callback.MvpCallback
import com.wangjj.app.mvp.moudle.LoginMoudle
import com.wangjj.app.net.response.LoginResponse
import com.wangjj.app.mvp.view.LoginView

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:42.
 * @Title
 */
class LoginPresenter : BasePresenter<LoginView>() {
    var loginView: LoginView? = null
    var loginMoudle: LoginMoudle = LoginMoudle()
    var activityContext: Context? = null

    override fun attachView(mvpView: LoginView) {
        this.loginView = mvpView
        activityContext = mvpView.getContext()
    }

    override fun detachView() {
        loginView = null
    }

    override fun isViewAttached(): Boolean {
        return loginView !== null
    }

    override fun getView(): LoginView {
        return this.loginView!!
    }

    fun loginAccount(userName: String, passWord: String) {
        loginView?.showLoading()
        loginMoudle.loginAccount(userName, passWord, object : MvpCallback<LoginResponse> {
            override fun onSuccess(data: LoginResponse) {
                loginView?.bindLoginData(data)
            }

            override fun onFailure(msg: String) {
                loginView?.showToast(msg)
            }

            override fun onError() {
                loginView?.showErr()
            }

            override fun onComplete() {
                loginView?.hideLoading()
            }

            override fun showLoading() {
                loginView?.showLoading()
            }

            override fun dissMissLoading() {
                loginView?.hideLoading()
            }

        })
    }

}