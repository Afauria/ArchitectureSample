package com.afauria.sample.architecture.mvcsample1

/**
 * Created by Afauria on 7/28/21.
 */

//class LoginActivity {
//    private val mController = LoginController()
//
//    fun onClick() {
//        mController.login("username", "password", this)
//    }
//
//    fun onLogin(name: String?) {
//        //runOnUIThread...
//    }
//}
//
////Controller负责业务逻辑处理和转发Callback
//class LoginController {
//    private val mModel = LoginModel()
//    //可以使用工具类、静态方法等
//    fun login(username: String?, password: String?, view: LoginActivity) {
//        //账号密码校验
//        //加密
//        mModel.login(username, password, view)
//    }
//}
//
//class LoginModel {
//
//    fun login(username: String?, password: String?, view: LoginActivity) {
//        //runOnIOThread...
//        view.onLogin("Afauria")
//    }
//}
//抽取接口IView
//如果IView起名为Callback？
interface IView {
    fun onLogin(name: String?)
}

class LoginActivity : IView {
    private val mController = LoginController()

    fun onClick() {
        mController.login("username", "password", this)
    }

    override fun onLogin(name: String?) {
        //runOnUIThread...
    }
}

//Controller负责业务逻辑处理和转发Callback
class LoginController {
    private val mModel = LoginModel()
    //可以使用工具类、静态方法等
    fun login(username: String?, password: String?, view: IView) {
        //账号密码校验
        mModel.login(username, password, view)
    }
}

class LoginModel {

    fun login(username: String?, password: String?, view: IView) {
        //runOnIOThread...
        view.onLogin("Afauria")
    }
}
