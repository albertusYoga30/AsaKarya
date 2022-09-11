package app.binar.synergy.android.asakarya.data.api.address

import com.google.gson.annotations.SerializedName

data class DetailAddressResponse(
    @SerializedName("code") var code: String? = null,
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("message") var message: String? = null
) {
    data class Data(
        @SerializedName("id") var id: Int? = null,
        @SerializedName("recipient") var recipient: String? = null,
        @SerializedName("phone") var phone: String? = null,
        @SerializedName("address") var address: String? = null,
        @SerializedName("postalCode") var postalCode: Int? = null,
        @SerializedName("main") var main: Boolean? = null,
        @SerializedName("userId") var userId: Int? = null,
        @SerializedName("createdAt") var createdAt: String? = null,
        @SerializedName("updatedAt") var updatedAt: String? = null,
        @SerializedName("deletedAt") var deletedAt: String? = null
    )
}