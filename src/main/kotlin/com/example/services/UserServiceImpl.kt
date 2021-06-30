package com.example.services

import com.example.models.MResponse
import com.example.domain.User
import com.example.models.UserDTO
import com.example.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserServiceImpl : UserService {

    @Inject
    lateinit var userRepository: UserRepository

    override fun saveUser(user: User, MResponse: MResponse<UserDTO>) {

        val tempUser = userRepository.findByEmail(user.email)

        if (tempUser != null) {
            MResponse.errors.add("Usuario já Cadastrado!")
            return
        }

        userRepository.apply {
            save(user)
            findByEmail(user.email)?.let { MResponse.response = UserDTO(it) }
        }
    }

    override fun delete(userId: Long, MResponse: MResponse<UserDTO>) {
        userRepository.deleteById(userId)
    }

    override fun findAll(mResponse: MResponse<List<UserDTO>>) {
        val userList = userRepository.findAll().map { UserDTO(it) }
        mResponse.response = userList
    }

    override fun findById(userId: Long, mResponse: MResponse<UserDTO>) {
        val user = userRepository.findById(userId)
        if (user.isPresent) {
            mResponse.response = UserDTO(user.get())
        } else {
            mResponse.errors.add("Id invalido!")
        }
    }
}