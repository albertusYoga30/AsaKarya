package app.binar.synergy.android.asakarya.ui.landing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.databinding.ActivityLandingPageBinding
import app.binar.synergy.android.asakarya.ui.Login.LoginActivity
import app.binar.synergy.android.asakarya.ui.Register.RegisterActivity

class LandingPageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLandingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonMasuk.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.buttonDaftar.setOnClickListener {
            startActivity((Intent(this,RegisterActivity::class.java )))
        }
    }
}