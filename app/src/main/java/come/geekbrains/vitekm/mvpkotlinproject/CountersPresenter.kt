package come.geekbrains.vitekm.mvpkotlinproject

import moxy.MvpPresenter


class CountersPresenter(
    private val model: CountersModel,
) : MvpPresenter<MainView>(){

    fun onCounterClickOne() {
        viewState.setTextOne(model.next(0).toString())
    }

    fun onCounterClickTwo() {
        viewState.setTextTwo(model.next(1).toString())
    }

    fun onCounterClickThree() {
        viewState.setTextThree(model.next(2).toString())
    }
}