package com.bchmsl.homework15.ui.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bchmsl.homework15.model.PostUser
import com.bchmsl.homework15.network.ResponseHandler
import com.bchmsl.homework15.network.RetrofitProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    private val _registerResponse = MutableStateFlow<ResponseHandler>(ResponseHandler.Loading())
    val registerResponse: StateFlow<ResponseHandler> get() = _registerResponse

    fun registerUser(user: PostUser){
        viewModelScope.launch {
            val response = RetrofitProvider.getRetrofit().registerUser(user)
            if (response.isSuccessful && response.body() != null){
                _registerResponse.emit(ResponseHandler.Success(response.body()))
            }else{
                _registerResponse.emit(ResponseHandler.Error(response.errorBody().toString()))
            }
            Log.wtf("TAG", response.body().toString())
        }
    }
}