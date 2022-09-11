package app.binar.synergy.android.asakarya.data.api.updateProfile

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(
    @SerializedName("code"    ) var code    : String? = null,
    @SerializedName("message" ) var message : String? = null
)
