package com.example.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var uid: Long = 0L,
        @Column(name = "name")
        var name: String = "Não informado",
        @Column(name = "email")
        var email: String = "Não informado",
        @Column(name = "password")
        var password: String = "",
        @Column(name = "description")
        var description: String = "Nenhuma descrição",
        @Column(name = "imageUrl")
        var imageUrl: String = "",
        @Column(name = "imageData")
        var imageData: ByteArray = byteArrayOf()
) {

    // Others
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (uid != other.uid) return false
        if (name != other.name) return false
        if (description != other.description) return false
        if (imageUrl != other.imageUrl) return false
        if (!imageData.contentEquals(other.imageData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + imageData.contentHashCode()
        return result
    }
}