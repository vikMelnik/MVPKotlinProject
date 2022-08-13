package come.geekbrains.vitekm.mvpkotlinproject.user.userinterface

interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?

    fun bindView(view: V)

    fun getCount(): Int
}