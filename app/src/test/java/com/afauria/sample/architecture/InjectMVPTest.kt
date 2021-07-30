package com.afauria.sample.architecture

import com.afauria.sample.architecture.injectmvp.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class InjectMVPTest {

    @MockK
    private lateinit var _activity: LoginContract.IView

    @MockK
    private lateinit var _model: LoginModel

    private lateinit var _presenter: LoginContract.IPresenter
    //私有方法mock：InternalPlatformDsl.dynamicCall

    private val _callback = slot<ResultCallback<String?>>()
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { _activity.onLogin(any()) } just Runs
        every { _activity.showError(any()) } just Runs
        every { _model.login(any(), any(), capture(_callback)) } just Runs
        //kotlin静态方法要添加@JvmStatic，否则无法mock
        mockkStatic(Injection::class)
        every { Injection.injectModel() } returns _model

        _presenter = LoginPresenter(_activity)
    }

    //模拟登录成功
    @Test
    fun testLoginPresenter() {
        _presenter.login("username", "password")

        //使用slot获取方法参数值，模拟成功回调
        _callback.captured.onSuccess("Afauria")
        verify(exactly = 1) { _model.login(any(), any(), any()) }
        verify(exactly = 1) { _activity.onLogin(any()) }
    }

    //模拟账号密码为空
    @Test
    fun testLoginPresenterEmpty() {
        _presenter.login(null, null)

        verify(exactly = 0) { _model.login(any(), any(), any()) }
        verify(exactly = 1) { _activity.showError(any()) }
    }

    //模拟账号已存在
    @Test
    fun testLoginPresenterExist() {
        _presenter.login("username", "password")

        //使用slot获取参数值，模拟失败回调
        _callback.captured.onFailure("errorCode", "is exist")
        verify(exactly = 1) { _model.login(any(), any(), any()) }
        verify(exactly = 1) { _activity.showError(any()) }
    }
}