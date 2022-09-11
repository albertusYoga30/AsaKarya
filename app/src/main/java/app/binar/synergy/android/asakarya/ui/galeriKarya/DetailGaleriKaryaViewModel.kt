package app.binar.synergy.android.asakarya.ui.galeriKarya

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignDetailResponse
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailGaleriKaryaViewModel(
    val sharedPreferences: SharedPreferences?) : ViewModel() {
    val galleryKarya: MutableLiveData<CampaignDetailResponse> = MutableLiveData()

    private lateinit var homeApi: HomeAPI

    fun onViewLoaded() {
        homeApi = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {

//            val response = homeApi.getCampaignById(
////                id = sharedPreferences.getInt(Const.CAMPAIGN_ID, 0)
//            )
//
//
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    Log.d("getAllGallery():: ", response.body().toString())
//                    galleryKarya.value = response.body()
//                } else{
//                    Log.d("getAllGallery() :: ", response.message())
//                }
//
//            }
        }
    }
}