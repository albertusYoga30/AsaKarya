package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.databinding.ActivityBookmarkBinding
import app.binar.synergy.android.asakarya.databinding.ActivityDetailDonasiBinding
import app.binar.synergy.android.asakarya.databinding.ActivityPermbayaranBerhasilBinding

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }
    }
}