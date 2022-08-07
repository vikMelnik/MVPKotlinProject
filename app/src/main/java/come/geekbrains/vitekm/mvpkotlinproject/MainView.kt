package come.geekbrains.vitekm.mvpkotlinproject

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType (AddToEndSingleStrategy::class)
interface MainView: MvpView {
    fun setTextOne(counters: String)
    fun setTextTwo(counters: String)
    fun setTextThree(counters: String)
}