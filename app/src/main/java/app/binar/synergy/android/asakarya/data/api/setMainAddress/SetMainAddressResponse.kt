package app.binar.synergy.android.asakarya.data.api.setMainAddress

import com.google.gson.annotations.SerializedName

data class SetMainAddressResponse (

    @SerializedName("code"    ) var code    : String? = null,
    @SerializedName("message" ) var message : String? = null

)