package com.android.modulization.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseBindingActivity<_ViewDataBinding : ViewBinding> : AppCompatActivity() {

    private var _binding: _ViewDataBinding? = null
    val binding: _ViewDataBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java
        )
        _binding = method.invoke(null, layoutInflater) as _ViewDataBinding
        setContentView(_binding!!.root)
        _binding?.onViewBindingCreated()
        onViewModelInit()
    }

    abstract fun _ViewDataBinding.onViewBindingCreated()
    abstract fun onViewModelInit()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}