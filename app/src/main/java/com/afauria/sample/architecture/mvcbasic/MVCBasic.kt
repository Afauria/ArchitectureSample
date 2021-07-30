package com.afauria.sample.architecture.mvcbasic

/**
 * Created by Afauria on 7/28/21.
 */
//把调用Model的逻辑抽出去，就是MVP了吗?
class LoginActivity {
    private val mModel = LoginModel()

    fun onClick() {
        //账号密码校验--->Controller逻辑
        //加密工具类
        mModel.login("username", "password", object : ResultCallback<String?> {
            override fun onSuccess(result: String?) {
                //回调方式返回数据
                onLogin(result)
                //View直接从Model层获取数据显示
                //setText(mModel.user)
            }

            override fun onFailure(errorCode: String?, errorMsg: String?) {}
        })
    }

    fun onLogin(name: String?) {
        //runOnUIThread...
    }
}

class LoginModel {
    var user: String? = null

    fun login(username: String?, password: String?, callback: ResultCallback<String?>) {
        //runOnIOThread...
        callback.onSuccess("Afauria")
    }
}

interface ResultCallback<T> {
    fun onSuccess(result: T)
    fun onFailure(errorCode: String?, errorMsg: String?)
}
