package app.binar.synergy.android.asakarya.ui.onBoarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.databinding.ActivityOnBoardingBinding
import app.binar.synergy.android.asakarya.ui.galangDana.GalangDanaActivity
import app.binar.synergy.android.asakarya.ui.landing.LandingPageActivity

class OnBoardingActivity : AppCompatActivity() {
    private var name = ""
    private lateinit var binding: ActivityOnBoardingBinding
    private val vp by lazy { findViewById<ViewPager2>(R.id.view_pager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = ViewPagerAdapter(this) { name = it.toString() }

        vp.adapter = pagerAdapter
        binding.dotsIndicator.setViewPager2(vp)

        binding.buttonAyoDukung.setOnClickListener {
            if (vp.currentItem < 2) {
                vp.currentItem = vp.currentItem.plus(1)
            } else {
                startActivity(Intent(this, LandingPageActivity::class.java))
            }
        }

        binding.buttonGalangDana.setOnClickListener {
            startActivity(Intent(this, GalangDanaActivity::class.java))
        }
    }
}

