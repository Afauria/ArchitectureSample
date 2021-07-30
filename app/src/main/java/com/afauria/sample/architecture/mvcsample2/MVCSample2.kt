package com.afauria.sample.architecture.mvcsample2

/**
 * Created by Afauria on 7/28/21.
 */
//https://zhuanlan.zhihu.com/p/83635530
interface IView {
    fun onLogin(name: String?)
}

class LoginActivity : IView {
    private val mModel = LoginModel(this)
    private val mController = LoginController(mModel)

    fun onClick() {
        mController.login("username", "password")
    }

    override fun onLogin(name: String?) {
        //runOnUIThread...
    }
}

class LoginController(private val mModel: LoginModel) {
    fun login(username: String?, password: String?) {
        //账号密码校验
        mModel.login(username, password)
    }
}

class LoginModel(private val view: IView) {

    fun login(username: String?, password: String?) {
        //runOnIOThread...
        view.onLogin("Afauria")
    }
}
