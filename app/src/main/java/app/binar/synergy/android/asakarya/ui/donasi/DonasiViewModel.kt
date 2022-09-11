package app.binar.synergy.android.asakarya.ui.donasi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DonasiViewModel: ViewModel() {
    val donasi: MutableLiveData<CampaignResponse> = MutableLiveData()
    var donasiPage = 1

    private lateinit var homeAPI: HomeAPI

    fun onViewLoaded() {
        homeAPI = HomeAPI.getInstance().create(
            HomeAPI::class.java
        )

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getCampaign(
                page = 0,
                size = 200,
                sortBy = "createdAt",
                sortType = "desc"
            )

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getCampaignFromAPI:: ", response.body().toString())
                    donasi.value = response.body()
                }
                else {
                    Log.d("getCampaignFromAPI:: ", response.message())
                }
            }
        }
    }
}