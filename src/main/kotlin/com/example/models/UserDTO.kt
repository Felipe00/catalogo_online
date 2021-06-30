package com.example.models

import com.example.domain.User

data class UserDTO(
        var uid: Long? = null,
        var name: String? = null,
        var email: String? = null,
        var password: String? = null,
        var description: String? = null,
        var imageUrl: String? = null
) {
    constructor(user: User) : this(
            name = user.name,
            description = user.description,
            email = user.email
    )

    fun toUse(): User? {
        return if (validade()) {
            User(
                    name = name!!,
                    email = email!!,
                    description = description!!,
                    imageUrl = imageUrl ?: "",
                    password = password ?: ""
            )
        } else {
            null
        }
    }

    fun validade() = !name.isNullOrEmpty() || !email.isNullOrEmpty()
}