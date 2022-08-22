package come.geekbrains.vitekm.mvpkotlinproject.allinterface

interface IImageLoader<T> {
    fun loadInfo(url: String, container: T)
}