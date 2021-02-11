package com.rafaelfelipeac.kotlinrepo.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafaelfelipeac.kotlinrepo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}
