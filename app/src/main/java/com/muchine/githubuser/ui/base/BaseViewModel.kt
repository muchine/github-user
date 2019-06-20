package com.muchine.githubuser.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    /**
     * Execute a given block in coroutines with a view model scope. This function encapsulates the internal details about
     * Coroutines scope and thread policy.
     */
    protected fun execute(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(block = block)
    }

}