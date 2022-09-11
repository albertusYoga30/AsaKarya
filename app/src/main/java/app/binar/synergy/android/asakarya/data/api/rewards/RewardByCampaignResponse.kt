package app.binar.synergy.android.asakarya.data.api.rewards

import app.binar.synergy.android.asakarya.data.api.donation.DonationsResponse
import com.google.gson.annotations.SerializedName

data class RewardByCampaignResponse(
    @SerializedName("code"    ) var code    : String? = null,
    @SerializedName("data"    ) var data    : List<Content> = arrayListOf(),
    @SerializedName("message" ) var message : String? = null
){
    data class Content(
        @SerializedName("id"           ) var id           : Int?     = null,
        @SerializedName("rewardTypeId" ) var rewardTypeId : Int?     = null,
        @SerializedName("campaignId"   ) var campaignId   : Int?     = null,
        @SerializedName("item"         ) var item         : String?  = null,
        @SerializedName("limited"      ) var limited      : Boolean? = null,
        @SerializedName("imgUrl"       ) var imgUrl       : String?  = null,
        @SerializedName("createdAt"    ) var createdAt    : String?  = null,
        @SerializedName("updatedAt"    ) var updatedAt    : String?  = null,
        @SerializedName("deletedAt"    ) var deletedAt    : String?  = null
    )
}
