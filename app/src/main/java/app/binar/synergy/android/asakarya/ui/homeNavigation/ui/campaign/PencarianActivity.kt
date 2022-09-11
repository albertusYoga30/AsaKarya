package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.databinding.ActivityPencarianBinding

class PencarianActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPencarianBinding
    private lateinit var viewModel: CampaignViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPencarianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences =
            this.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        viewModel = CampaignViewModel(sharedPreferences)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }

        binding.searchBar.setOnClickListener {
            binding.searchBar.setCompoundDrawables(null,null,null,null)
        }
//        val adapterSearching = AdapterDonasi(listOf(),
//            object : AdapterDonasi.EventListener {
//                override fun click(item: CampaignModel) {
//                }
//            })

//        binding.recycleviewSearching.adapter = adapterSearching

        viewModel.onViewLoaded()

//        viewModel.homeModel.observe(this, {
//            adapterSearching.update(it.campaignListModel)
//        })
    }
}