package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.tentangkami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.binar.synergy.android.asakarya.databinding.ActivityTentangKamiBinding

class TentangKamiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTentangKamiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTentangKamiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }
    }
}