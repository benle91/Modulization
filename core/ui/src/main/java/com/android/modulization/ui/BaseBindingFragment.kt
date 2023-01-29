package com.android.modulization.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.android.modulization.ui.supporter.NavigationSupporter
import java.lang.reflect.ParameterizedType

abstract class BaseBindingFragment<_ViewDataBinding : ViewBinding> : Fragment(),
    NavigationSupporter {

    private var _binding: _ViewDataBinding? = null
    val binding: _ViewDataBinding get() = _binding!!

    override val navController: NavController
        get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        _binding = method.invoke(null, layoutInflater, container, false) as _ViewDataBinding
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.onViewBindingCreated()
        onViewModelInit()
    }

    abstract fun _ViewDataBinding.onViewBindingCreated()
    abstract fun onViewModelInit()


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}