package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.ImageAPI
import app.binar.synergy.android.asakarya.data.api.profile.UserDetailResponse
import app.binar.synergy.android.asakarya.data.api.updateProfile.UpdateProfileRequest
import app.binar.synergy.android.asakarya.data.api.updateProfile.UpdateProfileResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class AkunViewModel(val sharedPreferences: SharedPreferences?) : ViewModel() {
    val profileDetail: MutableLiveData<UserDetailResponse> = MutableLiveData()

    private lateinit var homeAPI: HomeAPI

    fun onViewLoaded() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getUserDetail(
                investor_token = "Bearer "+sharedPreferences?.getString(Const.TOKEN, "").orEmpty()
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful){
                    Log.d("getUserDetailResponse:: ", response.body().toString())
                    profileDetail.value = response.body()
                } else {
                    Log.d("getUserDetailResponse:: ", response.message())
                }
            }
        }
    }


}
