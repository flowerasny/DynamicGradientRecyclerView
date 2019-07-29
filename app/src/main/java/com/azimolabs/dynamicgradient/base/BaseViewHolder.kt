package com.azimolabs.dynamicgradient.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.my_friend_message_item.view.*
import kotlinx.android.synthetic.main.my_message_item.view.*

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(viewModel: BaseViewModel)
}

class MyMessageViewHolder(private val view: View) : BaseViewHolder(view) {

    override fun bind(viewModel: BaseViewModel) {
        if (viewModel is MyMessageViewModel) {
            view.tvMyMessage.text = viewModel.message
        }
    }
}

class MyFriendMessageViewHolder(private val view: View) : BaseViewHolder(view) {
    override fun bind(viewModel: BaseViewModel) {
        if (viewModel is MyFriendMessageViewModel) {
            view.tvFriendMessage.text = viewModel.message
        }
    }
}