package app.binar.synergy.android.asakarya.data.api.bookmark

import com.google.gson.annotations.SerializedName

data class BookmarkResponse(
    @SerializedName("id"         ) var id         : Int?    = null,
    @SerializedName("userId"     ) var userId     : Int?    = null,
    @SerializedName("campaignId" ) var campaignId : Int?    = null,
    @SerializedName("createdAt"  ) var createdAt  : String? = null,
    @SerializedName("updatedAt"  ) var updatedAt  : String? = null,
    @SerializedName("deletedAt"  ) var deletedAt  : String? = null
)