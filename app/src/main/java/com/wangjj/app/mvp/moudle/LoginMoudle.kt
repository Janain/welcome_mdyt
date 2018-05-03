package com.wangjj.app.mvp.moudle

import com.wangjj.app.callback.MvpCallback
import com.wangjj.app.net.response.LoginResponse
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @Author Wangjj
 * @Create 2018/5/2  16:15.
 * @Title
 */
class LoginMoudle {

    fun loginAccount(userName: String, passWord: String, mvpCallback: MvpCallback<LoginResponse>) {
        var subscription: Subscription? = null
        subscription?.unsubscribe()
        subscription = RetrofitHelper.retrofitService.loginWanAndroid(
                userName,
                passWord
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mvpCallback.showLoading()
                }
                .subscribe(object : Subscriber<LoginResponse>() {
                    override fun onCompleted() {
                        subscription?.unsubscribe()
                        mvpCallback.dissMissLoading()
                    }

                    override fun onError(e: Throwable?) {
                        mvpCallback.onFailure(e.toString())
                    }

                    override fun onNext(t: LoginResponse?) {
                        mvpCallback.onSuccess(t!!)
                    }
                })
    }
}