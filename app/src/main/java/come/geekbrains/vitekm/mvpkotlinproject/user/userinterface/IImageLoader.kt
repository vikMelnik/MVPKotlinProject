package come.geekbrains.vitekm.mvpkotlinproject.user.userinterface

interface IImageLoader<T> {
    fun loadInfo(url: String, container: T)
}