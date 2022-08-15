package com.bchmsl.homework15.ui.login

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bchmsl.homework15.databinding.FragmentLoginBinding
import com.bchmsl.homework15.model.PostUser
import com.bchmsl.homework15.model.ResponseToken
import com.bchmsl.homework15.network.ResponseHandler
import com.bchmsl.homework15.ui.BaseFragment
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()
    override fun init() {
        listeners()
    }

    private fun listeners() {
        binding.btnLogin.setOnClickListener {
            if (fieldsAreValid()) {
                val user = PostUser(
                    binding.tilUsername.editText?.text.toString(),
                    binding.tilPassword.editText?.text.toString()
                )
                viewModel.loginUser(user)
                handleResponse()
            }
        }
    }

    private fun handleResponse(){
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.loginResponse.collect{
                binding.progressBar.isVisible = it.isLoading
                when(it){
                    is ResponseHandler.Success -> handleSuccess(it.successData)
                    is ResponseHandler.Error -> handleError(it.errorMessage)
                    else -> { }
                }
            }
        }
    }

    private fun handleError(errorMessage: String?) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess(body: ResponseToken?) {
        Toast.makeText(requireContext(), body?.token, Toast.LENGTH_SHORT).show()
    }

    private fun fieldsAreValid(): Boolean {
        with(binding) {
            tilUsername.error = null
            tilPassword.error = null
            when {
                tilUsername.editText?.text.toString().isEmpty() -> {
                    tilUsername.error = "This field is empty!"
                    return false
                }

                tilPassword.editText?.text.toString().isEmpty() -> {
                    tilPassword.error = "This field is empty!"
                    return false
                }
                else -> {
                    return true
                }
            }
        }
    }
}