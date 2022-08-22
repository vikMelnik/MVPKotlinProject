package come.geekbrains.vitekm.mvpkotlinproject.allinterface

interface RepoItemView: IItemView {
    fun setName(text: String)
    fun setDescription(text: String)
}