package com.example.userstory.data.datasource.remote

import android.util.Log
import com.example.userstory.di.IoDispatcher
import com.example.userstory.domain.model.UserDetail
import com.example.userstory.domain.model.UserInfo
import com.example.userstory.domain.repository.RestRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RestRepositoryImpl @Inject constructor(
    private val restApi: RestApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): RestRepository {

    private fun parseError(
        responseMessage: String?,
        responseCode: Int,
        errorBody: String?
    ): BaseNetworkException {

        val baseNetworkException = BaseNetworkException(responseMessage, responseCode)
        errorBody?.let {
            baseNetworkException.parseFromString(it)
        }

        return baseNetworkException
    }

    private fun parseNetworkErrorException(throwable: Throwable): NetworkErrorException {
        return NetworkErrorException(throwable.message)
    }

    private suspend fun <T : Any> callApi(call: suspend () -> Response<T>): T {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            Log.e("Frank", "BaseRemoteService ${t.message}")
            throw parseNetworkErrorException(t)
        }

        if (response.isSuccessful) {
            if (response.body() == null) {
                throw BaseNetworkException(
                    responseMessage = "Response without body",
                    responseCode = 200
                )
            } else {
                return response.body()!!
            }
        } else {
            val errorBody = response.errorBody()?.string() ?: ""
            throw parseError(response.message(), response.code(), errorBody)
        }
    }

    override suspend fun getUsers(perPage: Int, since: Int): List<UserInfo> =
        withContext(dispatcher) {
            val parameters = mutableMapOf<String, String>()
            parameters["per_page"] = perPage.toString()
            parameters["since"] = since.toString()
            callApi {
                restApi.getUsers(parameters)
            }
        }

    override suspend fun getUserDetail(loginName: String): UserDetail = withContext(dispatcher) {
        callApi {
            restApi.getUserDetail(loginName)
        }
    }
}