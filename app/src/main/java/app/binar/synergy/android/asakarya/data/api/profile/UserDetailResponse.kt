package app.binar.synergy.android.asakarya.data.api.profile

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(

    @SerializedName("code") var code: String? = null,
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("message") var message: String? = null

) {
    data class Data(

        @SerializedName("id") var id: Int? = null,
        @SerializedName("username") var username: String? = null,
        @SerializedName("fullName") var fullName: String? = null,
        @SerializedName("password") var password: String? = null,
        @SerializedName("phone") var phone: String? = null,
        @SerializedName("gender") var gender: String? = null,
        @SerializedName("dob") var dob: String? = null,
        @SerializedName("imgUrl") var imgUrl: String? = null,
        @SerializedName("roles") var roles: List<String> = arrayListOf(),
        @SerializedName("createdAt") var createdAt: String? = null,
        @SerializedName("updatedAt") var updatedAt: String? = null,
        @SerializedName("deletedAt") var deletedAt: String? = null

    )
}