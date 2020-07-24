@file:Suppress("DEPRECATION")

package com.wipro.assignment.mvvm.view.activity

import android.os.Bundle
import com.wipro.assignment.mvvm.R
import dagger.android.support.DaggerAppCompatActivity

class AboutActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }
}
