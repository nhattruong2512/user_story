package com.example.userstory.data.datasource.remote

open class NetworkErrorException (val responseMessage: String? = null): Exception()