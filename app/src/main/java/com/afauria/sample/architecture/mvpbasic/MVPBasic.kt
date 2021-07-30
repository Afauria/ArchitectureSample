package com.afauria.sample.architecture.mvpbasic

/**
 * Created by Afauria on 7/28/21.
 */
//定义Contract接口
interface LoginContract {
    interface IView {
        fun onLogin(name: String?)
    }

    interface IPresenter {
        fun login(username: String?, password: String?)
    }
}

//Activity、Fragment实现IView接口
class LoginActivity : LoginContract.IView {
    var mPresenter: LoginContract.IPresenter = LoginPresenter(this)

    fun onClick() {
        mPresenter.login("username", "password")
    }

    override fun onLogin(name: String?) {
        //runOnUIThread...
    }
}

//Presenter实现IPresenter接口
class LoginPresenter(private val mView: LoginContract.IView) : LoginContract.IPresenter {
    private val mModel = LoginModel()
    override fun login(username: String?, password: String?) {
        mModel.login(username, password, object : ResultCallback<String?> {
            override fun onSuccess(result: String?) {
                mView.onLogin(result)
            }

            override fun onFailure(errorCode: String?, errorMsg: String?) {}
        })
    }
}

interface ResultCallback<T> {
    fun onSuccess(result: T)
    fun onFailure(errorCode: String?, errorMsg: String?)
}

class LoginModel {
    fun login(username: String?, password: String?, callback: ResultCallback<String?>) {
        //runOnIOThread...
        callback.onSuccess("Afauria")
    }
}