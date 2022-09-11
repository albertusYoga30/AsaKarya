package app.binar.synergy.android.asakarya.data.api.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code") var code : String,
    @SerializedName("data") var data : String,
    @SerializedName("message") var message : String
)
