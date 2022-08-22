package come.geekbrains.vitekm.mvpkotlinproject.user.listuser

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import come.geekbrains.vitekm.mvpkotlinproject.databinding.ItemUserBinding
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.IImageLoader
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.IUserListPresenter
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.UserItemView

class UsersListAdapter(
    private val presenter: IUserListPresenter,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount(): Int = presenter.getCount()


    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            tvUserLogin.text = text
        }
        override fun setImageAvatar(url: String) = with(vb) {

                imageLoader.loadInfo(url,ivUserAvatar)

        }

    }
}