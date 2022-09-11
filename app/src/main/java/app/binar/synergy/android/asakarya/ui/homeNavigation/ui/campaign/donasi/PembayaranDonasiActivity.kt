package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.databinding.ActivityDetailDonasiBinding
import app.binar.synergy.android.asakarya.databinding.ActivityPembayaranBinding
import app.binar.synergy.android.asakarya.databinding.ActivityPembayaranDonasiBinding
import app.binar.synergy.android.asakarya.ui.payment.PembayaranActivity

class PembayaranDonasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranDonasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranDonasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }


        binding.inputCurrency.doAfterTextChanged {
            binding.layoutTotalDonasi.isVisible = true
        }
//        binding.inputCurrency.setOnClickListener {
//            binding.layoutTotalDonasi.isVisible = true
//
//        }

        binding.buttonPembayaran.setOnClickListener {
            startActivity(Intent(this, PembayaranActivity::class.java))

        }
    }

}