package app.binar.synergy.android.asakarya.ui.galeriKarya

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.databinding.ActivityDetailDonasiBinding
import app.binar.synergy.android.asakarya.databinding.ActivityGaleriKaryaBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.DetailDonasiViewModel

class GaleriKaryaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGaleriKaryaBinding
    private lateinit var viewModel: DetailGaleriKaryaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGaleriKaryaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)

        viewModel = DetailGaleriKaryaViewModel(sharedPreferences)
        binding.iconBack.setOnClickListener {
            onBackPressed()
        }
    }
}