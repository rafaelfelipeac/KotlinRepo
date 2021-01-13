package com.rafaelfelipeac.githubrepositories.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafaelfelipeac.githubrepositories.R
import com.rafaelfelipeac.githubrepositories.core.extension.viewBinding
import com.rafaelfelipeac.githubrepositories.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}