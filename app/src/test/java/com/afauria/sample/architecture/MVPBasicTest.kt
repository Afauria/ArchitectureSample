package com.afauria.sample.architecture

import com.afauria.sample.architecture.mvpbasic.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MVPBasicTest {
    @MockK
    private lateinit var _activity: LoginContract.IView

    //对Presenter进行测试，Presenter为真实对象，外部对象可mock，避免影响Presenter测试
    //Model在内部初始化，无法mock。说明存在耦合，不符合单元测试，如果Model中真实的访问网络和数据库会失败
    //什么叫方便单元测试？：
    // 1. 修改代码逻辑，替换Model
    // 2. 代码中加打印
    // 3. 将private改成public，通过mPresenter.model手动修改。
    //单元测试通过了，但影响了原来的代码逻辑，测完之后代码删掉，又跑不过了
    //正确的做法：对象由外部注入，mock对象
    private lateinit var _presenter: LoginContract.IPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        _presenter = LoginPresenter(_activity)
        every { _activity.onLogin(any()) } just Runs
    }

    @Test
    fun testLoginPresenter() {
        _presenter.login("username", "password")
        verify(exactly = 1) { _activity.onLogin("Afauria") }
    }
}