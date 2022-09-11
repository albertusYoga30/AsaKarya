package app.binar.synergy.android.asakarya.data.api.addAddress

import com.google.gson.annotations.SerializedName

data class AddAddressRequest(
    @SerializedName("recipient"  ) var recipient  : String? = null,
    @SerializedName("phone"      ) var phone      : String? = null,
    @SerializedName("address"    ) var address    : String? = null,
    @SerializedName("postalCode" ) var postalCode : Int?    = null
)
