package com.wangjj.app.base

/**
 * @Author Wangjj
 * @Create 2018/5/2  15:42.
 * @Title 抽象出Presente的公共方法
 */
abstract class BasePresenter<V : BaseView> {

    var mvpView: V? = null

    abstract fun attachView(mvpView: V)

    abstract fun detachView()

    abstract fun isViewAttached(): Boolean

    abstract fun getView(): V

}
