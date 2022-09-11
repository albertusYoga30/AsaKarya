package app.binar.synergy.android.asakarya.data.api.galleryKarya

import com.google.gson.annotations.SerializedName

data class AllGalleryResponse(

    @SerializedName("code") var code: String? = null,
    @SerializedName("data") var data: Data? = Data(),
    @SerializedName("message") var message: String? = null
) {
    data class Content(

        @SerializedName("id") var id: Int? = null,
        @SerializedName("title") var title: String? = null,
        @SerializedName("description") var description: String? = null,
        @SerializedName("website") var website: String? = null,
        @SerializedName("imgUrl1") var imgUrl1: String? = null,
        @SerializedName("imgUrl2") var imgUrl2: String? = null,
        @SerializedName("imgUrl3") var imgUrl3: String? = null,
        @SerializedName("promoted") var promoted: Boolean? = null,
        @SerializedName("creatorId") var creatorId: Int? = null,
        @SerializedName("creatorSocialMedia") var creatorSocialMedia: String? = null,
        @SerializedName("categoryId") var categoryId: Int? = null,
        @SerializedName("categoryName") var categoryName: String? = null,
        @SerializedName("createdAt") var createdAt: String? = null,
        @SerializedName("updatedAt") var updatedAt: String? = null,
        @SerializedName("deletedAt") var deletedAt: String? = null

    )

    data class Pageable(

        @SerializedName("pageNumber") var pageNumber: Int? = null,
        @SerializedName("pageSize") var pageSize: Int? = null,
        @SerializedName("totalElements") var totalElements: Int? = null

    )

    data class Data(

        @SerializedName("content") var content: List<Content> = arrayListOf(),
        @SerializedName("pageable") var pageable: Pageable? = Pageable()

    )
}