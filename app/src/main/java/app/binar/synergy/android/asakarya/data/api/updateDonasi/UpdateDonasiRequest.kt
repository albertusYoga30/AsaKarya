package app.binar.synergy.android.asakarya.data.api.updateDonasi

import com.google.gson.annotations.SerializedName

data class UpdateDonasiRequest(

    @SerializedName("id"             ) var id             : Int?    = null,
    @SerializedName("paymentSlipUrl" ) var paymentSlipUrl : String? = null
)