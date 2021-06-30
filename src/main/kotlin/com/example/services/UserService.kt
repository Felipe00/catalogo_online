package com.example.services

import com.example.models.MResponse
import com.example.domain.User
import com.example.models.UserDTO

interface UserService {

    fun saveUser(user: User, MResponse: MResponse<UserDTO>)

    fun delete(userId: Long, MResponse: MResponse<UserDTO>)

    fun findAll(mResponse: MResponse<List<UserDTO>>)

    fun findById(userId: Long, mResponse: MResponse<UserDTO>)

}