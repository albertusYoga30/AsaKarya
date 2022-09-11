package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.galeryKarya

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.galleryKarya.AllGalleryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GaleriKaryaViewModel(val sharedPreferences: SharedPreferences?) : ViewModel() {
    val galleryKarya: MutableLiveData<CampaignResponse> = MutableLiveData()

    private lateinit var homeApi: HomeAPI

    fun onViewLoaded() {
        homeApi = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            val response = homeApi.getCampaign(
                page = 0,
                size = 200,
                sortBy = "createdAt",
                sortType = "desc"
            )


            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("getAllGallery():: ", response.body().toString())
                    galleryKarya.value = response.body()
                } else{
                    Log.d("getAllGallery() :: ", response.message())
                }

            }
        }
    }
}