package come.geekbrains.vitekm.mvpkotlinproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repository(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String = "",
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("owner")
    val owner: GithubUser,
    @SerializedName("private")
    val private: Boolean = false,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("starred_url")
    val watchersCount: Int
) : Parcelable