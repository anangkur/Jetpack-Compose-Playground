package com.anangkur.jetpackcomposeplayground.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.graphics.imageFromResource
import androidx.ui.res.vectorResource
import com.anangkur.jetpackcomposeplayground.R

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            splashScreenContent(
                appTitle = getString(R.string.app_name),
                imageAsset = vectorResource(R.drawable.ic_baseline_adb_24)
            )
        }

        val handler = Handler()
        handler.postDelayed({

            finish()
        }, 1000)
    }
}