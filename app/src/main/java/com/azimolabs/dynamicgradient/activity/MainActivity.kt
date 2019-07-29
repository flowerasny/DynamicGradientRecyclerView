package com.azimolabs.dynamicgradient.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.azimolabs.dynamicgradient.R
import com.azimolabs.dynamicgradient.repository.messages
import com.azimolabs.dynamicgradient.utils.MassagesAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemAdapter = MassagesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dgrvMessages.layoutManager = LinearLayoutManager(this)
        dgrvMessages.setHasFixedSize(true)
        dgrvMessages.initialize(itemAdapter, R.id.tvMyMessage)

        itemAdapter.setItems(messages)
    }
}
