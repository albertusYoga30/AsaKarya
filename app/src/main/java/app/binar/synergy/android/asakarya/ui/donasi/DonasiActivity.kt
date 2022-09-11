package app.binar.synergy.android.asakarya.ui.donasi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.databinding.ActivityDonasiBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.DetailDonasiActivity

class DonasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDonasiBinding
    private lateinit var viewModel: DonasiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = DonasiViewModel()
        val sharedPreferences =
            this?.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }

        val adapterDonasiSaya = AdapterDonasi(listOf(),
            object : AdapterDonasi.EventListener {
                override fun click(item: CampaignResponse.Content) {
                    Toast.makeText(this@DonasiActivity, item.title, Toast.LENGTH_LONG).show()
                    sharedPreferences?.edit {
                        this.putInt(Const.CAMPAIGN_ID, item.id!!)
                        this.putInt(Const.CATEGORY_ID, item.categoryId!!)
                        apply()
                    }

                    startActivity( Intent(this@DonasiActivity, DetailDonasiActivity::class.java))
                }

            })

        binding.recycleviewDonasiSaya.adapter = adapterDonasiSaya

        viewModel.onViewLoaded()

        viewModel.donasi.observe(this, {
            adapterDonasiSaya.update(it.data!!.content)
        })
    }
}