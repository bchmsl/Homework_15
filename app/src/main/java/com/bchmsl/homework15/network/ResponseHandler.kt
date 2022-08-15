package com.bchmsl.homework15.network

import com.bchmsl.homework15.model.ResponseToken

sealed class ResponseHandler(
    open val isLoading: Boolean
) {
    class Success(val successData: ResponseToken?, override val isLoading: Boolean = false) :
        ResponseHandler(isLoading = false)

    class Error(val errorMessage: String?, override val isLoading: Boolean = false) :
        ResponseHandler(isLoading = false)

    class Loading(isLoading: Boolean = true) : ResponseHandler(isLoading)
}