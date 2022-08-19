package come.geekbrains.vitekm.mvpkotlinproject.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import come.geekbrains.vitekm.mvpkotlinproject.R
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInfo(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .placeholder(R.drawable.ic_user_placeholder)
            .error(R.drawable.error)
            .transition(DrawableTransitionOptions.withCrossFade(2500))
            .circleCrop()
            .into(container)
    }
}