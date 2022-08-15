package com.bchmsl.homework15.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bchmsl.homework15.model.PostUser
import com.bchmsl.homework15.network.ResponseHandler
import com.bchmsl.homework15.network.RetrofitProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _loginResponse = MutableStateFlow<ResponseHandler>(ResponseHandler.Loading())
    val loginResponse: StateFlow<ResponseHandler> get() = _loginResponse

    fun loginUser(user: PostUser){
        viewModelScope.launch {
            val response = RetrofitProvider.getRetrofit().loginUser(user)
            if (response.isSuccessful && response.body() != null){
                _loginResponse.emit(ResponseHandler.Success(response.body()))
            }else{
                _loginResponse.emit(ResponseHandler.Error(response.errorBody().toString()))
            }
            Log.wtf("TAG", response.body().toString())
        }
    }
}