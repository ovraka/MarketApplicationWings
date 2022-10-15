package com.assignment.marketapplicationwings.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.assignment.common.ext.AppResponse
import com.assignment.common.ext.BaseFragment
import com.assignment.marketapplicationwings.R
import com.assignment.marketapplicationwings.databinding.LayoutLoginFragmentBinding
import com.assignment.marketapplicationwings.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, LayoutLoginFragmentBinding>() {
    override val vm: LoginViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_login_fragment

    override fun initBinding(binding: LayoutLoginFragmentBinding) {
        super.initBinding(binding)
        binding.toRegister.setOnClickListener {
            vm.navigate(LoginFragmentDirections.loginToRegister())
        }
        binding.buttonLogin.setOnClickListener {
            vm.getLoginData(
                binding.usernameInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }
        vm.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is AppResponse.AppResponseSuccess -> {
                    Toast.makeText(this.context, it.data.message, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(LoginFragmentDirections.loginToProduct())
                }
                is AppResponse.AppResponseLoading -> {
                    Toast.makeText(this.context, "Please wait", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this.context, "Cannot connect because not same port", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}