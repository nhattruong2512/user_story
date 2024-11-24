package com.example.userstory.data.remote

open class NetworkErrorException (val responseMessage: String? = null): Exception()