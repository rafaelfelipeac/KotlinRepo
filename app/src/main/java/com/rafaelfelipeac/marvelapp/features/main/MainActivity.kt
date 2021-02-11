package com.rafaelfelipeac.marvelapp.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafaelfelipeac.marvelapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}
