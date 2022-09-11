package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.ubahProfil

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

class UbahProfilViewModel(val sharedPreferences: SharedPreferences) : ViewModel() {
    val updateProfile: MutableLiveData<UpdateProfileResponse> = MutableLiveData()
    val goToAkunFragment: MutableLiveData<Boolean> = MutableLiveData(false)
    val profileDetail: MutableLiveData<UserDetailResponse> = MutableLiveData()

    private lateinit var imageApi: ImageAPI
    private lateinit var homeAPI: HomeAPI

    private var fullname: String = ""
    private var email: String = ""
    private var phone: String = ""

    fun onChangeFullname(fullname: String) {
        this.fullname = fullname
    }

    fun onChangeEmail(email: String) {
        this.email = email
    }

    fun onChangePhone(phone: String) {
        this.phone = phone
    }

    fun onChangeImage(value: String) {
        sharedPreferences.edit {
            this.putString(Const.IMAGE, value)
            apply()
        }
    }

    fun onViewLoaded() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.getUserDetail(
                investor_token = "Bearer " + sharedPreferences.getString(Const.TOKEN, "").orEmpty()
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("getUserDetailResponse:: ", response.body().toString())
                    profileDetail.value = response.body()
                } else {
                    Log.d("getUserDetailResponse:: ", response.message())
                }
            }
        }
    }

    fun uploadImage(body: MultipartBody.Part) {
        imageApi = ImageAPI.getInstance().create(ImageAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = imageApi.uploadImage(image = body)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val image = response.body()?.data?.image?.url ?: ""
                    onChangeImage(image)
                }
            }
        }
    }

    fun updateProfile() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        val token = sharedPreferences.getString(Const.TOKEN, "").orEmpty()
        val request = UpdateProfileRequest(
            fullName = fullname,
            username = email,
            phone = phone,
            imgUrl = sharedPreferences.getString(Const.IMAGE, "").orEmpty()
        )
//        val request = UpdateProfileRequest(image = image)

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.updateProfile(
                investor_token = "bearer $token",
                request = request
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("UpdateProfileResponse:: ", response.body().toString())
                    updateProfile.value = response.body()
                    onChangeImage(request?.imgUrl.orEmpty())
                    goToAkunFragment.value = true
                } else {
                    Log.d("UpdateProfileResponse:: ", response.message())
                }
            }
        }
    }
}
