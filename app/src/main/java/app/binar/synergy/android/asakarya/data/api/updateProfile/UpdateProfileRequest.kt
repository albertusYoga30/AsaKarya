package app.binar.synergy.android.asakarya.data.api.updateProfile

import com.google.gson.annotations.SerializedName

data class UpdateProfileRequest(
    @SerializedName("fullName") var fullName: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("dob") var dob: String? = null,
    @SerializedName("imgUrl") var imgUrl: String? = null
)
