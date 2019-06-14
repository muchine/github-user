package com.muchine.githubuser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.muchine.githubuser.viewmodels.UserViewModel
import com.muchine.githubuser.viewmodels.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this, UserViewModelFactory).get(UserViewModel::class.java)
        userList.initialize(this, viewModel)
    }
}
