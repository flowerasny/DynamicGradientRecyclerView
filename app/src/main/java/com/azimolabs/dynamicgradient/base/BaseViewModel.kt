package com.azimolabs.dynamicgradient.base

sealed class BaseViewModel

data class MyMessageViewModel(val message: String) : BaseViewModel()
data class MyFriendMessageViewModel(val message: String) : BaseViewModel()