package com.azimolabs.dynamicgradient.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azimolabs.dynamicgradient.R
import com.azimolabs.dynamicgradient.base.*

class MassagesAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var baseViewModels: List<BaseViewModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        MY_MESSAGE_VIEW_TYPE -> MyMessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.my_message_item, parent, false
            )
        )
        MY_FRIEND_MESSAGE_VIEW_TYPE -> MyFriendMessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.my_friend_message_item, parent, false
            )
        )
        else -> throw IllegalArgumentException("Not supported view holder type: $viewType")
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind(baseViewModels[position])

    override fun getItemCount() = baseViewModels.size

    fun setItems(myViewModels: List<BaseViewModel>) {
        baseViewModels = myViewModels
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (baseViewModels[position]) {
        is MyMessageViewModel -> MY_MESSAGE_VIEW_TYPE
        is MyFriendMessageViewModel -> MY_FRIEND_MESSAGE_VIEW_TYPE
    }
}

const val MY_MESSAGE_VIEW_TYPE = 0
const val MY_FRIEND_MESSAGE_VIEW_TYPE = 1