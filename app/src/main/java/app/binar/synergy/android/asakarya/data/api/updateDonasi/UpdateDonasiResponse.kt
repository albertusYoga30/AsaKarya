package app.binar.synergy.android.asakarya.data.api.updateDonasi

import com.google.gson.annotations.SerializedName

data class UpdateDonasiResponse(
    @SerializedName("code") var code: String? = null,
    @SerializedName("message") var message: String? = null
)