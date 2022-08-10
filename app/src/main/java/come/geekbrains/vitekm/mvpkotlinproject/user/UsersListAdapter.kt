package come.geekbrains.vitekm.mvpkotlinproject.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import come.geekbrains.vitekm.mvpkotlinproject.databinding.ItemUserBinding
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.IUserListPresenter
import come.geekbrains.vitekm.mvpkotlinproject.user.userinterface.UserItemView

class UsersListAdapter(
    private val presenter: IUserListPresenter

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

    }
}