package com.example.controllers

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import java.security.Principal

@Secured("isAuthenticated()")
@Controller("/")
class LoginController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/")
    fun index(principal: Principal): String {
        return principal.name
    }
}