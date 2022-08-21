package come.geekbrains.vitekm.mvpkotlinproject.allinterface

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserInfoView:  ProgressView, ErrorView, MvpView {
    fun showLogin(text: String)
    fun setImageAvatar(url: String)
    fun showTopString(text: String)
    fun showCenterString(text: String)
    fun initList()
    fun updateList()

}
