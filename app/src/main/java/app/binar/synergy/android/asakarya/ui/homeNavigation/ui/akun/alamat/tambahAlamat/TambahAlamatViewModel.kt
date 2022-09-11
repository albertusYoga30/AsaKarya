package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.alamat.tambahAlamat

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.addAddress.AddAddressRequest
import app.binar.synergy.android.asakarya.data.api.addAddress.AddAddressResponse
import app.binar.synergy.android.asakarya.data.api.updateProfile.UpdateProfileRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TambahAlamatViewModel(val sharedPreferences: SharedPreferences): ViewModel() {
    val addAddress: MutableLiveData<AddAddressResponse> = MutableLiveData()
    val gotoAlamatPage: MutableLiveData<Boolean> = MutableLiveData(false)
    val showLoading: MutableLiveData<Boolean> =MutableLiveData(false)


    private lateinit var homeAPI: HomeAPI

    private var recepient: String = ""
    private var address: String = ""
    private var phone: String = ""
    private var postalCode: Int = 0

    fun onChangeReceipent(receipent: String) {
        this.recepient = receipent
    }

    fun onChangeAddress(address: String){
        this.address = address
    }

    fun onChangePhone(phone: String) {
        this.phone = phone
    }

    fun onChangePostalCode(postalCode: Int) {
        this.postalCode = postalCode
    }

//    fun addAddress() {
//        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)
//
//        val token = sharedPreferences.getString(Const.TOKEN, "").orEmpty()
//        val request = AddAddressRequest(
//            recipient = recepient,
//            address = address,
//            phone = phone,
//            postalCode = postalCode
//        )
////        val request = UpdateProfileRequest(image = image)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = homeAPI.addAddress(
//                request = request,
//                investor_token = "Bearer " + sharedPreferences.getString(Const.TOKEN, "").orEmpty()
//            )
//
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    Log.d("UpdateProfileResponse:: ", response.body().toString())
//                    updateProfile.value = response.body()
//                    onChangeImage(request?.imgUrl.orEmpty())
////                    goToAkunFragment.value = true
//                } else {
//                    Log.d("UpdateProfileResponse:: ", response.message())
//                }
//            }
//        }
//    }

    fun onViewLoaded(){
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        showLoading.value = true
        val request = AddAddressRequest(
            recipient = recepient,
            address = address,
            phone = phone,
            postalCode = postalCode
        )

        CoroutineScope(Dispatchers.IO).launch {
            val response = homeAPI.addAddress(
                request = request,
                investor_token = "Bearer " + sharedPreferences.getString(Const.TOKEN, "").orEmpty()
            )

            withContext(Dispatchers.Main) {
                if (response.isSuccessful){
                    Log.d("addAddress() :: ", response.body().toString())
                    addAddress.value = response.body()
                    gotoAlamatPage.value = true
                    showLoading.value = false
                } else {
                    showLoading.value = false
                    Log.d("addAddress() :: ", response.message())
                }
            }
        }
    }
}