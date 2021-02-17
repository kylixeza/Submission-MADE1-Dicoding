package com.kylix.submissionmade1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.kylix.submissionmade1.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val TIME_FOR_SPLASH = 3000L
        private const val VERSION = BuildConfig.VERSION_NAME
    }
    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        splashBinding.buildVersion.text = VERSION

        val handler = Handler(mainLooper)
        splashBinding.progress.start()
        splashBinding.progress.loadingColor = R.color.colorAccent
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            progress.stop()
            finish()
        }, TIME_FOR_SPLASH)
    }
}