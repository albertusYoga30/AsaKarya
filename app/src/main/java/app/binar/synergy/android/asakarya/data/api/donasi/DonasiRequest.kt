package app.binar.synergy.android.asakarya.data.api.donasi

import com.google.gson.annotations.SerializedName

data class DonasiRequest(
    @SerializedName("amount"     ) var amount     : Int?    = null,
    @SerializedName("notes"      ) var notes      : String? = null,
    @SerializedName("campaignId" ) var campaignId : Int?    = null,
    @SerializedName("addressId"  ) var addressId  : Int?    = null
)