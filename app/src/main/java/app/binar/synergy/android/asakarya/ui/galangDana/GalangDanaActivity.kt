package app.binar.synergy.android.asakarya.ui.galangDana

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.binar.synergy.android.asakarya.databinding.ActivityGalangDanaBinding

class GalangDanaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalangDanaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalangDanaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }
        binding.buttonBukaGalangDanaWeb.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://asakarya.vercel.app/"))
            startActivity(browserIntent)
        }
    }
}