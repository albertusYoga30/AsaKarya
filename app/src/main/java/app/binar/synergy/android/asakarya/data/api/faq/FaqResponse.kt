package app.binar.synergy.android.asakarya.data.api.faq

import com.google.gson.annotations.SerializedName

data class FaqResponse(
    @SerializedName("code") var code: String? = null,
    @SerializedName("data") var data: List<Content>,
    @SerializedName("message") var message: String? = null
) {
    data class Content(
        @SerializedName("id") var id: Int? = null,
        @SerializedName("question") var question: String? = null,
        @SerializedName("answer") var answer: String? = null,
        @SerializedName("campaignId") var campaignId: Int? = null,
        @SerializedName("creatorId") var creatorId: Int? = null,
        @SerializedName("profileFullName") var profileFullName: String? = null,
        @SerializedName("profileImage") var profileImage: String? = null,
        @SerializedName("createdAt") var createdAt: String? = null,
        @SerializedName("updatedAt") var updatedAt: String? = null,
        @SerializedName("deletedAt") var deletedAt: String? = null
    )
}
