package app.binar.synergy.android.asakarya.data.api.profile

import app.binar.synergy.android.asakarya.data.api.donation.DonationsResponse
import com.google.gson.annotations.SerializedName

data class UpdateProfileResquest(
    @SerializedName("code"    ) var code    : String? = null,
    @SerializedName("data"    ) var data    : Data?   = Data(),
    @SerializedName("message" ) var message : String? = null
){
    data class Data(
        @SerializedName("fullName" ) var fullName : String? = null,
        @SerializedName("phone"    ) var phone    : String? = null,
        @SerializedName("username" ) var username : String? = null,
        @SerializedName("gender"   ) var gender   : String? = null,
        @SerializedName("dob"      ) var dob      : String? = null,
        @SerializedName("imgUrl"   ) var imgUrl   : String? = null
    )
}
