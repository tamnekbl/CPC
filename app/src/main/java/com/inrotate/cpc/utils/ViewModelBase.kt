package com.inrotate.cpc.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class ViewModelBase<T : Any> : ViewModel() {

    protected val _state = MutableLiveData<T>()

    val state: LiveData<T>
        get() = _state

}