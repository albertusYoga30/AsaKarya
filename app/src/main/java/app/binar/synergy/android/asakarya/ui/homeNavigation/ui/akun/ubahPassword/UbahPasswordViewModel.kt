package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.UbahPassword

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.ImageAPI
import app.binar.synergy.android.asakarya.data.api.profile.UserDetailResponse
import app.binar.synergy.android.asakarya.data.api.updatePassword.UpdatePasswordRequest
import app.binar.synergy.android.asakarya.data.api.updatePassword.UpdatePasswordResponse
import app.binar.synergy.android.asakarya.data.api.updateProfile.UpdateProfileRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class UbahPasswordViewModel(val sharedPreferences: SharedPreferences): ViewModel() {
    val updatePassword : MutableLiveData<UpdatePasswordResponse> = MutableLiveData()
    val detailProfile: MutableLiveData<UserDetailResponse> = MutableLiveData()
    val showMessage: MutableLiveData<String> = MutableLiveData()
    val gotoSplashScreen: MutableLiveData<Boolean> = MutableLiveData(false)
    val showLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    private lateinit var homeAPI: HomeAPI


    private var oldPass: String = ""
    private var newPass: String = ""
    private var renewPass: String = ""

    fun onChangeOldPass(oldPass: String){
        this.oldPass = oldPass
    }

    fun onChangeNewPass(newPass: String) {
        this.newPass = newPass
    }

    fun onChangerenewPass(renewPass: String){
        this.renewPass = renewPass
    }

    fun onViewLoaded() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getUserDetail(
                investor_token = "Bearer "+sharedPreferences.getString(Const.TOKEN, "").orEmpty()
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful){
                    Log.d("getUserDetailResponse :: ", response.body().toString())
                    detailProfile.value = response.body()
                } else {
                    Log.d("getUserDetailResponse :: ", response.message())
                }
            }
        }
    }

    fun updatePassword() {
        if(newPass.equals(renewPass)){

            homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

            showLoading.value = true

            val request = UpdatePasswordRequest(
                oldPassword = oldPass,
                newPassword = newPass
            )

            CoroutineScope(Dispatchers.IO).launch {
                val response = homeAPI.updatePassword(
                    request = request,
                    investor_token = "Bearer "+sharedPreferences.getString(Const.TOKEN, "").orEmpty()
                )

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful){
                        Log.d("getUserDetailResponse:: ", response.body().toString())
                        updatePassword.value = response.body()
                        sharedPreferences.edit {
                            this.putBoolean(Const.IS_LOGIN, false)
                            apply()
                        }
                        gotoSplashScreen.value = true
                        showMessage.value = response.message().toString()
                        showLoading.value = false
                    } else {
                        showLoading.value = false
                        Log.d("getUserDetailResponse:: ", response.message())
                    }
                    showLoading.value = false
                }
            }
        } else {
            showMessage.value = "Password tidak sama"
        }
    }

}