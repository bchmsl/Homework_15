package com.bchmsl.homework15.ui.main

import androidx.navigation.fragment.findNavController
import com.bchmsl.homework15.databinding.FragmentMainBinding
import com.bchmsl.homework15.ui.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun init() {
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToLoginFragment())
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToRegisterFragment())
        }
    }

}