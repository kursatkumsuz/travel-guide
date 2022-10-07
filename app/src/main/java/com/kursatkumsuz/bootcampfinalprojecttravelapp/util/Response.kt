package com.kursatkumsuz.bootcampfinalprojecttravelapp.util

enum class ResponseStatus {
    SUCCESS,
    ERROR,
    LOADING
}

data class Response(val status: ResponseStatus, val message: String?) {

    companion object {

        fun success(msg : String): Response {
            return Response(ResponseStatus.SUCCESS, msg)
        }

        fun loading(): Response {
            return Response(ResponseStatus.LOADING, null)
        }

        fun error(msg: String): Response {
            return Response(ResponseStatus.ERROR, msg)
        }
    }
}