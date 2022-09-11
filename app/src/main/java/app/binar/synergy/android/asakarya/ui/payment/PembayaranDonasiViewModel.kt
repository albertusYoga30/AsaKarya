package app.binar.synergy.android.asakarya.ui.payment

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.address.DetailAddressResponse
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignDetailResponse
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.donasi.DonasiRequest
import app.binar.synergy.android.asakarya.data.api.donasi.DonasiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class PembayaranDonasiViewModel(val sharedPreferences: SharedPreferences): ViewModel() {
    val donasi: MutableLiveData<DonasiResponse> = MutableLiveData()
    val detailDonasi: MutableLiveData<CampaignDetailResponse> = MutableLiveData()
    val gotoPembayaran: MutableLiveData<Boolean> = MutableLiveData(false)
    val detailAlamat: MutableLiveData<DetailAddressResponse> = MutableLiveData()

    private lateinit var homeApi: HomeAPI

    private var amount: Int = 0
    private var notes: String = ""

    fun onChangeAmount(amount: Int){
        this.amount = amount
    }

    fun onChangeNotes(notes: String) {
        this.notes = notes
    }

    fun doAddDonasi() {

        homeApi = HomeAPI.getInstance().create(HomeAPI::class.java)

        val request = DonasiRequest(
            amount = amount,
            notes = notes,
            campaignId = sharedPreferences.getInt(Const.CAMPAIGN_ID, 0),
            addressId = sharedPreferences.getInt(Const.ADDRESS_ID, 0)
        )

        Log.d("Address Id:: ", sharedPreferences.getInt(Const.ADDRESS_ID, 0).toString())

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeApi.addDonation(
                request = request,
                investor_token = "Bearer " +sharedPreferences.getString(Const.TOKEN, "").orEmpty()
            )

            Log.d("Response add Donation:: ", response.body().toString())

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    sharedPreferences.edit {
                        this.putInt(Const.TOTAL_DONASI, amount)
                        this.putInt(Const.DONASI_ID, response.body()?.id ?: 0)
                    }
                    donasi.value = response.body()
                    gotoPembayaran.value = true
                } else {
                    Log.d("addDonation():: ", response.message())
                }

            }
        }

    }

    fun onViewLoaded() {
        homeApi = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            val responseCampaign = homeApi.getCampaignById(
                id = sharedPreferences.getInt(Const.CAMPAIGN_ID, 0)
            )

            val responseAddress = homeApi.getDetailAddress(
                id = sharedPreferences.getInt(Const.ADDRESS_ID, 0),
                investor_token = "Bearer " + sharedPreferences.getString(Const.TOKEN, "").orEmpty()
            )

            withContext(Dispatchers.Main) {

                Log.d("Response addresss:: ", responseAddress.isSuccessful.toString())
                if (responseCampaign.isSuccessful) {
                    detailDonasi.value = responseCampaign.body()
//                    sharedPreferences.edit {
//                        this.putInt(Const.TOTAL_DONASI, responseCampaign.body()?.data?.sumDonation ?: 0)
//                    }
                } else {
                    Log.d("getCampaignById():: ", responseCampaign.message())
                }


                if(responseAddress.isSuccessful) {
                    Log.d("getDetailAddress():: ", responseAddress.body().toString())
                    detailAlamat.value = responseAddress.body()
                } else {
                    Log.d("getDetailAddress():: ",responseAddress.message())
                }
            }
        }


    }
}