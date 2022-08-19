package come.geekbrains.vitekm.mvpkotlinproject.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubUserNew (
    val login: String,
    val id: Int): Parcelable
