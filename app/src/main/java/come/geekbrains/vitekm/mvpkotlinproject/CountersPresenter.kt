package come.geekbrains.vitekm.mvpkotlinproject

class CountersPresenter(
    private val view: MainView,
    private val model: CountersModel,
) {
    fun onCounterClickOne() {
        view.setTextOne(model.next(0).toString())
    }

    fun onCounterClickTwo() {
        view.setTextTwo(model.next(1).toString())
    }

    fun onCounterClickThree() {
        view.setTextThree(model.next(2).toString())
    }
}