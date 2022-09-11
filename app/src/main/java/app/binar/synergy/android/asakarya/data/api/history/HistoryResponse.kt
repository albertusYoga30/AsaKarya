package app.binar.synergy.android.asakarya.data.api.history

import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("code") var code: String? = null,
    @SerializedName("data") var data: Data?   = Data(),
    @SerializedName("message") var message: String? = null
){
    data class Content(
        @SerializedName("id"              ) var id              : Int?    = null,
        @SerializedName("title"           ) var title           : String? = null,
        @SerializedName("activity"        ) var activity        : String? = null,
        @SerializedName("imgUrl"          ) var imgUrl          : String? = null,
        @SerializedName("campaignId"      ) var campaignId      : Int?    = null,
        @SerializedName("creatorId"       ) var creatorId       : Int?    = null,
        @SerializedName("profileFullName" ) var profileFullName : String? = null,
        @SerializedName("profileImage"    ) var profileImage    : String? = null,
        @SerializedName("createdAt"       ) var createdAt       : String? = null,
        @SerializedName("updatedAt"       ) var updatedAt       : String? = null,
        @SerializedName("deletedAt"       ) var deletedAt       : String? = null
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
