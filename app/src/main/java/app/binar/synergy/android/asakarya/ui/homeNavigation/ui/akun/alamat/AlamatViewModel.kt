package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.alamat

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.address.AddressResponse
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.setMainAddress.SetMainAddressResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlamatViewModel(val sharedPreferences: SharedPreferences): ViewModel() {
    val address: MutableLiveData<AddressResponse> = MutableLiveData()
    val setMain: MutableLiveData<SetMainAddressResponse> = MutableLiveData()
    val isMainAddress: MutableLiveData<Boolean> = MutableLiveData(false)

    private lateinit var homeAPI: HomeAPI

    fun setMainAddress() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.setMainAdrres(
                id = sharedPreferences.getInt(Const.ADDRESS_ID, 0),
                investor_token = "Bearer "+sharedPreferences.getString(Const.TOKEN, null)
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful){
                    setMain.value = response.body()
                    isMainAddress.value = true
                    Log.d("Set Main Address :: ", response.body().toString())
                } else {
                    Log.d("Set Main address response :: ", response.message())
                }
            }
        }
    }

    fun onViewLoaded() {
        homeAPI = HomeAPI.getInstance().create(
            HomeAPI::class.java
        )

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getAllAddress(
                investor_token = "Bearer " + sharedPreferences.getString(Const.TOKEN, null)
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful){
                    address.value = response.body()
                    Log.d("getAllAddressByUserId() :: ", response.body()?.data.toString())
                } else {
                    Log.d("getAllAddressByUserId() ::", response.message())
                }
            }
        }


    }
}