package app.binar.synergy.android.asakarya.data.api.reward

import com.google.gson.annotations.SerializedName

class RewardResponse(
    @SerializedName("code") var code: String? = null,
    @SerializedName("data") var data: List<Data> = arrayListOf(),
    @SerializedName("message") var message: String? = null
) {
    data class Data(

        @SerializedName("id") var id: Int? = null,
        @SerializedName("rewardTypeId") var rewardTypeId: Int? = null,
        @SerializedName("campaignId") var campaignId: Int? = null,
        @SerializedName("item") var item: String? = null,
        @SerializedName("limited") var limited: Boolean? = null,
        @SerializedName("imgUrl") var imgUrl: String? = null,
        @SerializedName("createdAt") var createdAt: String? = null,
        @SerializedName("updatedAt") var updatedAt: String? = null,
        @SerializedName("deletedAt") var deletedAt: String? = null

    )
}