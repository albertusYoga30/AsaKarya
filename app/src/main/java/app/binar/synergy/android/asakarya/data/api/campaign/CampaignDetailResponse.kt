package app.binar.synergy.android.asakarya.data.api.campaign

import com.google.gson.annotations.SerializedName

data class CampaignDetailResponse (
    @SerializedName("code"    ) var code    : String? = null,
    @SerializedName("data"    ) var data    : Data?   = Data(),
    @SerializedName("message" ) var message : String? = null
){
    data class Data (

        @SerializedName("id"                  ) var id                  : Int?    = null,
        @SerializedName("title"               ) var title               : String? = null,
        @SerializedName("description"         ) var description         : String? = null,
        @SerializedName("fundAmount"          ) var fundAmount          : Int?    = null,
        @SerializedName("due"                 ) var due                 : String? = null,
        @SerializedName("imgUrl"              ) var imgUrl              : String? = null,
        @SerializedName("website"             ) var website             : String? = null,
        @SerializedName("location"            ) var location            : String? = null,
        @SerializedName("daysLeft"            ) var daysLeft            : Int?    = null,
        @SerializedName("sumDonation"         ) var sumDonation         : Int?    = null,
        @SerializedName("countDonation"       ) var countDonation       : Int?    = null,
        @SerializedName("status"              ) var status              : Int?    = null,
        @SerializedName("categoryId"          ) var categoryId          : Int?    = null,
        @SerializedName("categoryName"        ) var categoryName        : String? = null,
        @SerializedName("creatorId"           ) var creatorId           : Int?    = null,
        @SerializedName("creatorOrganization" ) var creatorOrganization : String? = null,
        @SerializedName("profileFullName"     ) var profileFullName     : String? = null,
        @SerializedName("profileImage"        ) var profileImage        : String? = null,
        @SerializedName("createdAt"           ) var createdAt           : String? = null,
        @SerializedName("updatedAt"           ) var updatedAt           : String? = null,
        @SerializedName("deletedAt"           ) var deletedAt           : String? = null
    )
}