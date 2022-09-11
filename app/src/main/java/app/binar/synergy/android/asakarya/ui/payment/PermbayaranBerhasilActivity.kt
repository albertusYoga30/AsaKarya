package app.binar.synergy.android.asakarya.ui.payment

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.binar.synergy.android.asakarya.databinding.ActivityPermbayaranBerhasilBinding
import app.binar.synergy.android.asakarya.databinding.FragmentCampaignBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.CampaignFragment
import app.binar.synergy.android.asakarya.ui.Login.LoginActivity
import app.binar.synergy.android.asakarya.ui.donasi.DonasiActivity


class PermbayaranBerhasilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPermbayaranBerhasilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermbayaranBerhasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOk.setOnClickListener {
            startActivity(Intent(this, DonasiActivity::class.java).apply {
                this.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                )
            })

        }
    }
}