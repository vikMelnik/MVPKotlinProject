package come.geekbrains.vitekm.mvpkotlinproject

class CountersPresenter(
    private val view: MainView,
    private val model: CountersModel,
) {

    fun onCounterClick(id: Int) {
        when (id) {
            R.id.btnNumber1 -> {
                val newValue = model.next(0)
                view.setText(newValue.toString())


            }
            R.id.btnNumber2 -> {
                val newValue = model.next(1)
                view.setText(newValue.toString())
            }
            R.id.btnNumber3 -> {
                val newValue = model.next(2)
                view.setText(newValue.toString())
            }
        }
    }

}