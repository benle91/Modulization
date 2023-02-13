package com.android.modulization.auth.signup

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.databinding.FragmentSignUpBinding
import com.android.modulization.data.local.models.FormFieldState
import com.android.modulization.data.preference.PrefRepository
import com.android.modulization.data.preference.PreferenceKey
import com.android.modulization.ui.BaseBindingFragment
import com.android.modulization.ui.ScreenEventState
import com.android.modulization.ui.ScreenEventViewModel
import com.android.modulization.utils.addTextAndColor
import com.android.modulization.utils.onSingleClick
import com.android.modulization.utils.setHtmlText
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *Created by Hien on 2/12/2023.
 */
class SignUpFragment : BaseBindingFragment<FragmentSignUpBinding>() {

    private val mViewModel by viewModel<SignUpViewModel>()
    private val mSharedPreference: PrefRepository by inject()
    private val mScreenEventViewModel by activityViewModels<ScreenEventViewModel>()

    override fun FragmentSignUpBinding.onViewBindingCreated() {
        setUpUI()
    }

    override fun onViewModelInit() {
        with(mViewModel) {
            ldValidFirstName.observe(viewLifecycleOwner) { state ->
                binding.tvErrorFirstName.visibility = when (state) {
                    FormFieldState.OK -> View.INVISIBLE
                    else -> View.VISIBLE
                }
            }
            ldValidLastName.observe(viewLifecycleOwner) { state ->
                binding.tvErrorLastName.visibility = when (state) {
                    FormFieldState.OK -> View.INVISIBLE
                    else -> View.VISIBLE
                }
            }
            ldValidEmail.observe(viewLifecycleOwner) { state ->
                binding.tvErrorEmail.visibility = when (state) {
                    FormFieldState.OK -> View.INVISIBLE
                    else -> View.VISIBLE
                }
            }
            ldValidPassword.observe(viewLifecycleOwner) { state ->
                binding.tvErrorPassword.visibility = when (state) {
                    FormFieldState.OK -> View.INVISIBLE
                    else -> View.VISIBLE
                }
            }
            ldAccountResponse.observe(viewLifecycleOwner) { response ->
                lifecycleScope.launch {
                    mSharedPreference.putString(PreferenceKey.ACCESS_TOKEN, response.token)
                    mScreenEventViewModel.navigateToScreen(ScreenEventState.NAVIGATE_TO_ROOT)
                }
            }
            ldAuthThrowable.observe(viewLifecycleOwner) { throwable ->
            }
        }
    }

    private fun FragmentSignUpBinding.setUpUI() {
        txtFirstName.addTextAndColor(
            "*",
            ContextCompat.getColor(requireContext(), R.color.colorError)
        )
        txtLastName.addTextAndColor(
            "*",
            ContextCompat.getColor(requireContext(), R.color.colorError)
        )
        txtEmail.addTextAndColor("*", ContextCompat.getColor(requireContext(), R.color.colorError))
        txtPassword.addTextAndColor(
            "*",
            ContextCompat.getColor(requireContext(), R.color.colorError)
        )
        cbAuthPolicy.setHtmlText(resources.getString(R.string.cb_auth_agree_to_policy))
        tvHaveAccount2GoSignIn.setHtmlText(resources.getString(R.string.txt_auth_already_have_an_account))
        btSignUp.onSingleClick {
            mViewModel.createAccount(
                edFirstName.text?.trim().toString(),
                edLastName.text?.trim().toString(),
                edEmail.text?.trim().toString(),
                edPassword.text?.trim().toString()
            )
        }
        tvHaveAccount2GoSignIn.onSingleClick {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    override fun onCustomBackPresses(): Boolean {
        return true
    }

}