package come.geekbrains.vitekm.mvpkotlinproject.user.listuserrepo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.IUserRepoListPresenter
import come.geekbrains.vitekm.mvpkotlinproject.allinterface.RepoItemView
import come.geekbrains.vitekm.mvpkotlinproject.databinding.ItemUserRepositoryInfoBinding

class UserListRepoAdapter  (   val presenter: IUserRepoListPresenter
) : RecyclerView.Adapter<UserListRepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(

        ItemUserRepositoryInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    ).apply {
        itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })

    override fun getItemCount(): Int = presenter.getCount()


    inner class ViewHolder(val vb: ItemUserRepositoryInfoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override fun setName(text: String) = with(vb) {
            tvName.text = text
        }

        override fun setDescription(text: String) = with(vb) {
            tvDescription.text = text
        }

        override var pos = -1
    }
}