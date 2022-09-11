package app.binar.synergy.android.asakarya.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.ui.homeNavigation.HomeNavigationActivity
import app.binar.synergy.android.asakarya.ui.landing.LandingPageActivity
import app.binar.synergy.android.asakarya.ui.onBoarding.OnBoardingActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        val firstStart : Boolean = sharedPreferences.getBoolean(Const.IS_FIRST_START, true)

        Handler(Looper.getMainLooper()).postDelayed({
            if (isLogin(sharedPreferences)){
                startActivity(Intent(this, HomeNavigationActivity::class.java))
                finish()
            }
            else if (firstStart){
                showOnBoardingPage(sharedPreferences)
            }
            else {
                startActivity(Intent(this, LandingPageActivity::class.java))
                finish()
            }
        },3000)
    }

    private fun isLogin(sharedPreferences: SharedPreferences) : Boolean {
        return sharedPreferences.getBoolean(Const.IS_LOGIN, false)
    }

    private fun showOnBoardingPage(sharedPreferences: SharedPreferences) {
        startActivity(Intent(this, OnBoardingActivity::class.java))

        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(Const.IS_FIRST_START, false)
        editor.apply()
    }
}