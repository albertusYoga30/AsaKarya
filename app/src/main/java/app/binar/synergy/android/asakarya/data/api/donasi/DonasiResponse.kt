package app.binar.synergy.android.asakarya.data.api.donasi

import com.google.gson.annotations.SerializedName

data class DonasiResponse(
    @SerializedName("code") var code: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("message") var message: String? = null
)