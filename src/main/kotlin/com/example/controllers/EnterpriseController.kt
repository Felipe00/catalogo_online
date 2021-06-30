package com.example.controllers

import com.example.models.MResponse
import com.example.domain.User
import com.example.models.UserDTO
import com.example.services.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import javax.inject.Inject

@Controller("/admin")
class EnterpriseController {

    @Inject
    lateinit var userService: UserService

    @Secured("isAuthenticated()")
    @Post()
    fun save(@Body userDto: UserDTO): HttpResponse<MResponse<UserDTO>> {
        val response = MResponse<UserDTO>()
        val user = userDto.toUse()

        return if (user != null) {
            userService.saveUser(user, response)
            HttpResponse.ok()
        } else {
            response.errors.add("Usuario invalido!")
            HttpResponse.badRequest(response)
        }
    }

    @Get()
    fun findAll(): HttpResponse<MResponse<List<UserDTO>>>{
        val response = MResponse<List<UserDTO>>()
        userService.findAll(response)
        return HttpResponse.ok(response)
    }

    @Secured("isAuthenticated()")
    @Delete("/{id}")
    fun delete(@Part id: Long): HttpResponse<MResponse<UserDTO>>{
        val response = MResponse<UserDTO>()
        userService.delete(id, response)
        return HttpResponse.ok(response)
    }

    @Get("/{id}")
    fun findById(@Part id: Long): HttpResponse<MResponse<UserDTO>>{
        val response = MResponse<UserDTO>()
        userService.findById(id, response)
        return HttpResponse.ok(response)
    }
}