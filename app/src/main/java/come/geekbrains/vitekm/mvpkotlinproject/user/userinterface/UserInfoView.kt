package come.geekbrains.vitekm.mvpkotlinproject.user.userinterface


import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface UserInfoView:  ProgressView, ErrorView, MvpView {
    fun showLogin(text: String)
}
