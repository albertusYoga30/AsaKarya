package app.binar.synergy.android.asakarya.data.api.updatePassword

import com.google.gson.annotations.SerializedName

data class UpdatePasswordResponse (
    @SerializedName("code"    ) var code    : String? = null,
    @SerializedName("message" ) var message : String? = null
        )