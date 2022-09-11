package app.binar.synergy.android.asakarya.ui.payment

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.ImageAPI
import app.binar.synergy.android.asakarya.data.api.address.DetailAddressResponse
import app.binar.synergy.android.asakarya.data.api.donasi.DonasiResponse
import app.binar.synergy.android.asakarya.data.api.updateDonasi.UpdateDonasiRequest
import app.binar.synergy.android.asakarya.data.api.updateDonasi.UpdateDonasiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

//
class PembayaranViewModel(val sharedPreferences: SharedPreferences): ViewModel() {
    val updateDonasi : MutableLiveData<UpdateDonasiResponse> = MutableLiveData()
    val gotoThankyouPage : MutableLiveData<Boolean> = MutableLiveData(false)

    private lateinit var homeApi: HomeAPI
    private lateinit var imageAPI: ImageAPI

    fun doUpdateDonation(image: String) {
        homeApi = HomeAPI.getInstance().create(HomeAPI::class.java)

        val request = UpdateDonasiRequest(
            id = sharedPreferences.getInt(Const.DONASI_ID, 0),
            paymentSlipUrl = image
        )

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeApi.updateDonation(
                request = request,
                investor_token = sharedPreferences.getString(Const.TOKEN, "").orEmpty()
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("updateDonation():: ", response.message().toString())
                    updateDonasi.value = response.body()
                    gotoThankyouPage.value = true
                } else {
                    Log.d("updateDonation():: ", response.message().toString())
                }
            }
        }
    }

    fun onChangeImage(value: String) {
        sharedPreferences.edit {
            this.putString(Const.IMAGE_PAYMENT, value)
            apply()
        }
    }

    fun uploadImage(body: MultipartBody.Part) {
        imageAPI = ImageAPI.getInstance().create(ImageAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = imageAPI.uploadImage(image = body)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val image = response.body()?.data?.image?.url ?: ""
                    val thumb = response.body()?.data?.image?.url ?: ""

                    onChangeImage(image)
//
//                    val profile = ProfileModel(
//                        image = image,
//                        name = sharedPreferences.getString(Const.FULLNAME, "") ?: "",
//                        job = sharedPreferences.getString(Const.JOB, "") ?: ""
//                    )
//
//                    profileModel.value = profile
                    doUpdateDonation(image)
                }
            }
        }
    }
}