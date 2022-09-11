package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignByCategoryResponse
import app.binar.synergy.android.asakarya.data.api.address.DetailAddressResponse
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignDetailResponse
import app.binar.synergy.android.asakarya.data.api.donation.DonationsResponse
import app.binar.synergy.android.asakarya.data.api.faq.FaqResponse
import app.binar.synergy.android.asakarya.data.api.history.HistoryResponse
import app.binar.synergy.android.asakarya.data.api.rewards.RewardByCampaignResponse
import app.binar.synergy.android.asakarya.data.api.reward.RewardResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailDonasiViewModel(
    val sharedPreferences: SharedPreferences) : ViewModel() {
    val campaignDetail: MutableLiveData<CampaignDetailResponse> = MutableLiveData()
    val history: MutableLiveData<HistoryResponse> = MutableLiveData()
    val messages: MutableLiveData<DonationsResponse> = MutableLiveData()
    val faq: MutableLiveData<FaqResponse> = MutableLiveData()
    val rewards: MutableLiveData<RewardByCampaignResponse> = MutableLiveData()
    val recommendCampaign: MutableLiveData<CampaignByCategoryResponse> = MutableLiveData()

    private lateinit var homeAPI: HomeAPI

    fun getHistoryFromAPI(){
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = homeAPI.getHistory(
                id = sharedPreferences.getInt(Const.CAMPAIGN_ID, 0),
                page = 0,
                size = 1,
                sortBy = "updatedAt",
                sortType = "desc"

            )
            Log.d("Response", response.message())

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getHistory():: ", response.body().toString())
                    history.value = response.body()
                } else{
                    Log.d("getHistoryFromAPI() :: ", response.message())
                }
            }
        }
    }

    fun getMessagesFromAPI(){
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = homeAPI.getMessages(
                id = sharedPreferences.getInt(Const.CAMPAIGN_ID, 0),
                page = 0,
                size = 1,
                sortBy = "createdAt",
                sortType = "desc"

            )
            Log.d("Response", response.message())

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getMessages():: ", response.body().toString())
                    messages.value = response.body()
                } else{
                    Log.d("getMessagesFromAPI() :: ", response.message())
                }
            }
        }
    }

    fun getFaqFromAPI(){
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = homeAPI.getFaq(
                id = sharedPreferences.getInt(Const.CAMPAIGN_ID, 0)
            )
            Log.d("Response", response.message())

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getFaq():: ", response.body().toString())
                    faq.value = response.body()
                } else{
                    Log.d("getFaqFromAPI() :: ", response.message())
                }
            }
        }
    }

    fun getRewardsFromAPI(){
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = homeAPI.getRewardByCampaign(
                id = sharedPreferences.getInt(Const.CAMPAIGN_ID, 0)
            )
            Log.d("Response", response.message())

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getRewardByCampign():: ", response.body().toString())
                    rewards.value = response.body()
                } else{
                    Log.d("getRewardByCampign():: ", response.message())
                }
            }
        }
    }
    fun getRecommendCampaignFromAPI(){
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            val response = homeAPI.getRecommendCampaign(
                id = sharedPreferences.getInt(Const.CATEGORY_ID, 0),
                page = 0,
                size = 6,
                sortBy = "createdAt",
                sortType = "desc"
            )
            Log.d("Response", response.message())

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {
                    Log.d("getRecommendCampaignFromAPI():: ", response.body().toString())
                    recommendCampaign.value = response.body()
                } else{
                    Log.d("getRecommendCampaignFromAPI():: ", response.message())
                }
            }
        }
    }

    fun onViewLoaded() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getCampaignById(
                id = sharedPreferences.getInt(Const.CAMPAIGN_ID, 0)
            )

            withContext(Dispatchers.Main) {
                Log.d("Response Detail Donasi::", response.isSuccessful.toString())
                if (response.isSuccessful){
                    Log.d("getDetailDonasiFromAPI():: ", response.body().toString())
                    campaignDetail.value = response.body()
                } else{
                    Log.d("getDetailDonasiFromAPI():: ", response.message())
                }
            }
        }
        getHistoryFromAPI()
        getMessagesFromAPI()
        getFaqFromAPI()
        getRewardsFromAPI()
        getRecommendCampaignFromAPI()
    }
}