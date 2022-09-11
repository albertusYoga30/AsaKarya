package app.binar.synergy.android.asakarya.data.api.updatePassword

import com.google.gson.annotations.SerializedName

data class UpdatePasswordRequest(
    @SerializedName("oldPassword" ) var oldPassword : String? = null,
    @SerializedName("newPassword" ) var newPassword : String? = null
)
