package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.messages

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.donation.DonationsResponse
import app.binar.synergy.android.asakarya.databinding.ActivityMessagesBinding

class MessagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesBinding
    private lateinit var viewModel: MessagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)

        viewModel = MessagesViewModel(sharedPreferences)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }

        val adapterMessages = AdapterMessages(listOf(),
        object :AdapterMessages.EventListener{
            override fun click(item: DonationsResponse.Content) {
                TODO("Not yet implemented")
            }
        })

        binding.recycleviewMessages.adapter = adapterMessages

        viewModel.messages.observe(this, {
            adapterMessages.update(it.data!!.content)
            binding.emptyMessages.isVisible = adapterMessages.itemCount <= 0
        })

        viewModel.onViewLoaded()
    }
}