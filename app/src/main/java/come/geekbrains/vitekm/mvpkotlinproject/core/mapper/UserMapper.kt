package come.geekbrains.vitekm.mvpkotlinproject.core.mapper

import come.geekbrains.vitekm.mvpkotlinproject.core.network.UserDto
import come.geekbrains.vitekm.mvpkotlinproject.model.GithubUser

object UserMapper {
    fun mapToEntity(dto: UserDto): GithubUser {
        return GithubUser(
            id = dto.id,
            login = dto.login,
            avatarUrl = dto.avatarUrl.toString()
        )
    }

}