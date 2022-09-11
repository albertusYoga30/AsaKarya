package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.campaign.DonasiCepatResponse
import app.binar.synergy.android.asakarya.data.api.profile.UserDetailResponse
import app.binar.synergy.android.asakarya.model.homeModel.BannerModel
import app.binar.synergy.android.asakarya.model.homeModel.CampaignModel
import app.binar.synergy.android.asakarya.model.homeModel.HomeListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CampaignViewModel(val sharedPreferences: SharedPreferences?) : ViewModel() {
    val campaign: MutableLiveData<CampaignResponse> = MutableLiveData()
    val donasiCepat: MutableLiveData<List<DonasiCepatResponse.Content>> = MutableLiveData()
    val profileDetail: MutableLiveData<UserDetailResponse> = MutableLiveData()


    private lateinit var homeAPI: HomeAPI

    val homeModel: MutableLiveData<HomeListModel> = MutableLiveData()

    fun getDonasiCepatFromAPI() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            val response = homeAPI.getDonasiCepat(
                page = 0,
                size = 4,
                sortBy = "title",
                sortType = "desc"
            )
            Log.d("Response", response.message())

            withContext(Dispatchers.Main) {
                Log.d("response2:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getDonasiCepatFromAPI():: ", response.body().toString())
                    donasiCepat.value = response.body()?.data?.content?.filter {
                        it -> it.daysLeft!! <= 30
                    }
                } else{
                    Log.d("getDonasiCepatFromAPI() :: ", response.message())
                }

            }
        }
    }

    fun getCampaignFromAPI() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getCampaign(
                page =0,
                size = 4,
                sortBy = "createdAt",
                sortType = "desc"
            )

            Log.d("Response", response.message())

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getDataFromAPI():: ", response.body().toString())

                    campaign.value = response.body()
                } else{
                    Log.d("getDataFromAPI() :: ", response.errorBody().toString())
                }
            }
        }
    }
    fun getUserFromAPI() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getUserDetail(
                investor_token = "Bearer "+sharedPreferences?.getString(Const.TOKEN, "").orEmpty()
            )

            Log.d("Response", response.message())

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getDataFromAPI():: ", response.body().toString())

                    profileDetail.value = response.body()
                } else{
                    Log.d("getDataFromAPI() :: ", response.errorBody().toString())
                }
            }
        }
    }

    fun onViewLoaded() {
        val bannerList = listOf(
            BannerModel(
                image = R.drawable.banner_campaign
            ),
            BannerModel(
                image = R.drawable.banner_campaign
            ),
            BannerModel(
                image = R.drawable.banner_campaign
            ),
            BannerModel(
                image = R.drawable.banner_campaign
            ),
        )

        homeModel.value = HomeListModel(
            bannerListModel = bannerList
//            campaignListModel = campaignList
        )
        getUserFromAPI()
        getCampaignFromAPI()
        getDonasiCepatFromAPI()
    }


}

