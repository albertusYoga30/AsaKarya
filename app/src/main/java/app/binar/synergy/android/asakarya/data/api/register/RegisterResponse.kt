package app.binar.synergy.android.asakarya.data.api.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("code") var code : String,
    @SerializedName("message") var message : String
)
