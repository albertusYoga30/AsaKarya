package app.binar.synergy.android.asakarya.data.api.register

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

    @SerializedName("username") var username: String,
    @SerializedName("fullName") var fullName: String,
    @SerializedName("password") var password: String,
    @SerializedName("phone") var phone: String
)