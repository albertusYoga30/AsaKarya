package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.donasiSaya

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.donasiSaya.getDonationsByUserIdResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DonasiSayaViewModel(val sharedPreferences: SharedPreferences?) : ViewModel() {
    val donasiSaya: MutableLiveData<getDonationsByUserIdResponse> = MutableLiveData()
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData(true)
    private lateinit var homeAPI: HomeAPI

    fun onViewLoaded(){
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            val response = homeAPI.getDonationsByUserId(
                page = 0,
                size = 10,
                sortBy = "createdAt",
                sortType = "desc",
                investor_token = "Bearer" + sharedPreferences?.getString(Const.TOKEN, "")
            )

            Log.d("Response get donations by user id", response.message())

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("getDonationByUserId():: ", response.body().toString())
                    donasiSaya.value = response.body()
                    isEmpty.value = false
                } else{
                    Log.d("getDonationByUserId()() :: ", response.message())
                }

            }
        }
    }
}