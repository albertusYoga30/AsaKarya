package app.binar.synergy.android.asakarya.ui.Register

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.binar.synergy.android.asakarya.data.api.HomeAPI
import app.binar.synergy.android.asakarya.data.api.register.RegisterRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern.compile

private val PHONE_REGEX =
    compile("^(^\\+62|62|^08)(\\d{3,4}-?){2}\\d{3,4}\$")
private val PASSWORD_REGEX = compile("""^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$""")

class RegisterViewModel() : ViewModel() {
    val isButtonEnable: MutableLiveData<Boolean> = MutableLiveData(false)
    val goToLogin: MutableLiveData<Boolean> = MutableLiveData(false)
    val showErrorMessage: MutableLiveData<String> = MutableLiveData()
    val showErrorInput: MutableLiveData<String> = MutableLiveData()
    val validate: MutableLiveData<Boolean> = MutableLiveData(false)
    val showLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    private lateinit var homeAPI: HomeAPI

    private var name: String = ""
    private var email: String = ""
    private var password: String = ""
    private var phoneNumber: String = ""

    fun onChangeEmail(email: String) {
        this.email = email
        validateInput()
    }

    fun onChangePassword(password: String) {
        this.password = password
        validateInput()
    }

    fun onChangePhone(phone: String) {
        this.phoneNumber = phone
        validateInput()
    }

    fun onChangeName(name: String) {
        this.name = name
        validateInput()
    }

    //untuk mendisable button
    fun validateInput() {
        isButtonEnable.value =
            email.isNotEmpty() &&
                    password.isNotEmpty() &&
                    name.isNotEmpty() &&
                    phoneNumber.isNotEmpty()
    }

    fun validateInputValue() {
        if (!validateName(name)) {
            showErrorInput.value = "Name must be 3 caracter or more"
        } else if (!validateEmailAddress(email)) {
            showErrorInput.value = "Invalid Email Address"
        } else if (!validatePhone(phoneNumber)) {
            showErrorInput.value = "Invalid phone number"
        } else if (!validatePassword(password)) {
            showErrorInput.value =
                "Password must containt at least 8 caracter and at least 1 number "
        } else if (validateEmailAddress(email) &&
            validatePassword(password) &&
            validateName(name) &&
            validatePhone(phoneNumber)
        ) {
            validate.value = true
        }
    }

    fun validateEmailAddress(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePhone(phone: String): Boolean {
        return PHONE_REGEX.matcher(phoneNumber).matches()
    }

    fun validatePassword(password: String): Boolean {
        return PASSWORD_REGEX.matcher(password).matches()
    }

    fun validateName(name: String): Boolean {
        return name.length >= 3
    }


    fun doRegister() {
        homeAPI = HomeAPI.getInstance().create(HomeAPI::class.java)

        showLoading.value = true

        CoroutineScope(Dispatchers.IO).launch {
            val request = RegisterRequest(
                username = email,
                fullName = name,
                password = password,
                phone = phoneNumber
            )

            val response = homeAPI.postRegister(request)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    showLoading.value = false
                    goToLogin.value = true
                } else {
                    showLoading.value = false
                    showErrorMessage.value = response.errorBody()?.string()
                }
            }
        }
    }

}