package com.android.modulization.auth.signin

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.databinding.FragmentSignInBinding
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
class SignInFragment: BaseBindingFragment<FragmentSignInBinding>() {

    private val mViewModel by viewModel<SignInViewModel>()
    private val mSharedPreference: PrefRepository by inject()
    private val mScreenEventViewModel by activityViewModels<ScreenEventViewModel>()

    override fun FragmentSignInBinding.onViewBindingCreated() {
        setUpUI()
    }

    override fun onViewModelInit() {
        with(mViewModel) {
            ldValidEmail.observe(viewLifecycleOwner) { state ->
                binding.tvErrorEmail.visibility = when (state) {
                    FormFieldState.MUST_BE_REQUIRE -> View.VISIBLE
                    else -> View.INVISIBLE
                }
            }
            ldValidPassword.observe(viewLifecycleOwner) { state ->
                binding.tvErrorPassword.visibility = when (state) {
                    FormFieldState.MUST_BE_REQUIRE -> View.VISIBLE
                    else -> View.INVISIBLE
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


    private fun FragmentSignInBinding.setUpUI() {
        txtEmail.addTextAndColor("*", ContextCompat.getColor(requireContext(), R.color.colorError))
        txtPassword.addTextAndColor(
            "*",
            ContextCompat.getColor(requireContext(), R.color.colorError)
        )
        tvNewOnOurPlatform.setHtmlText(resources.getString(R.string.txt_auth_new_on_our_platform))
        btSignIn.onSingleClick {
            mViewModel.authorizationAccount(
                edEmail.text?.trim().toString(),
                edPassword.text?.trim().toString()
            )
        }
        tvNewOnOurPlatform.onSingleClick {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun onCustomBackPresses(): Boolean {
        return true
    }

}