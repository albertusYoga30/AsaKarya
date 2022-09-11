package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.messages

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.donation.DonationsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessagesViewModel(
    val sharedPreferences: SharedPreferences): ViewModel() {
    val messages: MutableLiveData<DonationsResponse> = MutableLiveData()

    private lateinit var homeAPI: HomeAPI

    fun onViewLoaded(){
        homeAPI = HomeAPI.getInstance().create(
            HomeAPI::class.java
        )
        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getMessages(
                id = sharedPreferences.getInt(Const.CAMPAIGN_ID, 0) ,
                page = 0,
                size = 50,
                sortBy = "createdAt",
                sortType = "desc"
            )

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getCampaignFromAPI:: ", response.body().toString())
                    messages.value = response.body()
                }
                else {
                    Log.d("getCampaignFromAPI:: ", response.message())
                }
            }
        }

    }
}