package com.example.models

data class MResponse<T>(
        var response: T? = null
) {
    val errors = ArrayList<String>()
}