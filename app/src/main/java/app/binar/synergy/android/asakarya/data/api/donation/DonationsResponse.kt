package app.binar.synergy.android.asakarya.data.api.donation
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import com.google.gson.annotations.SerializedName

data class DonationsResponse(
    @SerializedName("code"    ) var code    : String? = null,
    @SerializedName("data"    ) var data    : Data?   = Data(),
    @SerializedName("message" ) var message : String? = null
){
    data class Content(
        @SerializedName("id"                   ) var id                   : Int?    = null,
        @SerializedName("amount"               ) var amount               : Int?    = null,
        @SerializedName("paymentSlipUrl"       ) var paymentSlipUrl       : String? = null,
        @SerializedName("notes"                ) var notes                : String? = null,
        @SerializedName("status"               ) var status               : Int?    = null,
        @SerializedName("addressId"            ) var addressId            : Int?    = null,
        @SerializedName("userId"               ) var userId               : Int?    = null,
        @SerializedName("profileFullName"      ) var profileFullName      : String? = null,
        @SerializedName("profileImage"         ) var profileImage         : String? = null,
        @SerializedName("campaignId"           ) var campaignId           : Int?    = null,
        @SerializedName("campaignTitle"        ) var campaignTitle        : String? = null,
        @SerializedName("campaignImage"        ) var campaignImage        : String? = null,
        @SerializedName("campaignCategoryName" ) var campaignCategoryName : String? = null,
        @SerializedName("campaignCreatorName"  ) var campaignCreatorName  : String? = null,
        @SerializedName("rewardId"             ) var rewardId             : Int?    = null,
        @SerializedName("rewardType"           ) var rewardType           : String? = null,
        @SerializedName("rewardItem"           ) var rewardItem           : String? = null,
        @SerializedName("createdAt"            ) var createdAt            : String? = null,
        @SerializedName("updatedAt"            ) var updatedAt            : String? = null,
        @SerializedName("deletedAt"            ) var deletedAt            : String? = null

    )

    data class Pageable(
        @SerializedName("pageNumber"    ) var pageNumber    : Int? = null,
        @SerializedName("pageSize"      ) var pageSize      : Int? = null,
        @SerializedName("totalElements" ) var totalElements : Int? = null
    )

    data class Data (
        @SerializedName("content"  ) var content  : List<Content> = arrayListOf(),
        @SerializedName("pageable" ) var pageable : Pageable?     = Pageable()

    )

}