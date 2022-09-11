package app.binar.synergy.android.asakarya.data.api.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("data") var data: Data
) {
    data class Data(

        @SerializedName("username") var username: String,
        @SerializedName("password") var password: String

    )
}