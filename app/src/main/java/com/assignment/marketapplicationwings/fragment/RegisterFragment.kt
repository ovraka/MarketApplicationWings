package com.assignment.marketapplicationwings.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.assignment.common.ext.AppResponse
import com.assignment.common.ext.BaseFragment
import com.assignment.common.response.register.RegisterResponse
import com.assignment.marketapplicationwings.R
import com.assignment.marketapplicationwings.databinding.LayoutRegisterFragmentBinding
import com.assignment.marketapplicationwings.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterViewModel, LayoutRegisterFragmentBinding>() {
    override val vm: RegisterViewModel by viewModels()
    override val layoutResourceId: Int = R.layout.layout_register_fragment

    override fun initBinding(binding: LayoutRegisterFragmentBinding) {
        super.initBinding(binding)
        binding.buttonRegister.setOnClickListener {
            vm.getRegisterData(
                binding.usernameInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }

        vm.registerState.observe(viewLifecycleOwner) {
            when (it) {
                is AppResponse.AppResponseSuccess -> {
                    if (!it.data.error) {
                        Toast.makeText(this.context, it.data.message, Toast.LENGTH_SHORT).show()
                        vm.popBackStack()
                    } else {
                        Toast.makeText(this.context, it.data.message, Toast.LENGTH_SHORT).show()
                    }
                }
                is AppResponse.AppResponseError -> {
                    Toast.makeText(this.context, "Failed register, try again", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}