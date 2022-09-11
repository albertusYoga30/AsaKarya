package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.history

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.donation.DonationsResponse
import app.binar.synergy.android.asakarya.data.api.history.HistoryResponse
import app.binar.synergy.android.asakarya.databinding.ActivityHistoryBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.messages.AdapterMessages
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.messages.MessagesViewModel

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)

        viewModel = HistoryViewModel(sharedPreferences)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }

        val adapterHistory = AdapterHistory(listOf(),
            object : AdapterHistory.EventListener{
                override fun click(item: HistoryResponse.Content) {
                    TODO("Not yet implemented")
                }
            })

        binding.recyclerviewNews.adapter = adapterHistory

        viewModel.history.observe(this, {
            adapterHistory.update(it.data!!.content)
            binding.emptyHistory.isVisible = adapterHistory.itemCount <= 0
        })

        viewModel.onViewLoaded()
    }
}