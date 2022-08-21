package come.geekbrains.vitekm.mvpkotlinproject.allinterface

import come.geekbrains.vitekm.mvpkotlinproject.allinterface.ProgressView
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView, ProgressView {
    fun initList()
    fun updateList()
}
