package app.binar.synergy.android.asakarya.ui.Login

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.login.LoginRequest
import com.google.gson.Gson
import kotlinx.coroutines.*

class LoginViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    val isButtonEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    val gotoHomePage: MutableLiveData<Boolean> = MutableLiveData(false)
    val showErrorMessage: MutableLiveData<String> = MutableLiveData()
    val showLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    private lateinit var homeAPI: HomeAPI

    private var email: String = ""
    private var password: String = ""

    fun validateInput() {
        isButtonEnable.value = email.isNotEmpty() && password.isNotEmpty()
    }

    fun onChangeEmail(email: String) {
        this.email = email
        validateInput()
    }

    fun onChangePassword(password: String) {
        this.password = password
        validateInput()
    }

    fun doLogin() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        showLoading.value = true

        val request = LoginRequest.Data(username = email, password = password)

        CoroutineScope(Dispatchers.IO).launch {

            val response = homeAPI.postLogin(
                request
            )


            Log.d("username", email)
            Log.d("password", password)

            withContext(Dispatchers.Main) {
                Log.d("response:: ", response.isSuccessful.toString())
                if (response.isSuccessful) {

                    val loginResponse = response.body()

                    sharedPreferences.edit {
                        this.putBoolean(Const.IS_LOGIN, true)
                        this.putString(Const.TOKEN, loginResponse?.data)
                        apply()
                    }
                    showLoading.value = false
                    gotoHomePage.value = true
                } else {
                    showLoading.value = false
                    showErrorMessage.value = response.errorBody()?.string()
                }
//                showLoading.value = false
            }
        }
    }
}

