package app.binar.synergy.android.asakarya.data.api

import app.binar.synergy.android.asakarya.data.api.addAddress.AddAddressRequest
import app.binar.synergy.android.asakarya.data.api.addAddress.AddAddressResponse
import app.binar.synergy.android.asakarya.data.api.address.AddressResponse
import app.binar.synergy.android.asakarya.data.api.address.DetailAddressResponse
import app.binar.synergy.android.asakarya.data.api.bookmark.BookmarkResponse
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignByCategoryResponse
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignDetailResponse
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.campaign.DonasiCepatResponse
import app.binar.synergy.android.asakarya.data.api.donasi.DonasiRequest
import app.binar.synergy.android.asakarya.data.api.donasi.DonasiResponse
import app.binar.synergy.android.asakarya.data.api.donasiSaya.getDonationsByUserIdResponse
import app.binar.synergy.android.asakarya.data.api.donation.DonationsResponse
import app.binar.synergy.android.asakarya.data.api.faq.FaqResponse
import app.binar.synergy.android.asakarya.data.api.galleryKarya.AllGalleryResponse
import app.binar.synergy.android.asakarya.data.api.history.HistoryResponse
import app.binar.synergy.android.asakarya.data.api.login.LoginRequest
import app.binar.synergy.android.asakarya.data.api.login.LoginResponse
import app.binar.synergy.android.asakarya.data.api.profile.UserDetailResponse
import app.binar.synergy.android.asakarya.data.api.register.RegisterRequest
import app.binar.synergy.android.asakarya.data.api.register.RegisterResponse
import app.binar.synergy.android.asakarya.data.api.rewards.RewardByCampaignResponse
import app.binar.synergy.android.asakarya.data.api.reward.RewardResponse
import app.binar.synergy.android.asakarya.data.api.setMainAddress.SetMainAddressResponse
import app.binar.synergy.android.asakarya.data.api.updateDonasi.UpdateDonasiRequest
import app.binar.synergy.android.asakarya.data.api.updateDonasi.UpdateDonasiResponse
import app.binar.synergy.android.asakarya.data.api.updatePassword.UpdatePasswordRequest
import app.binar.synergy.android.asakarya.data.api.updatePassword.UpdatePasswordResponse
import app.binar.synergy.android.asakarya.data.api.updateProfile.UpdateProfileRequest
import app.binar.synergy.android.asakarya.data.api.updateProfile.UpdateProfileResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.net.PasswordAuthentication
import java.util.concurrent.TimeUnit


interface HomeAPI {
    @POST("/user/login")
    suspend fun postLogin(@Body request: LoginRequest.Data): Response<LoginResponse>

    @POST("user/register")
    suspend fun postRegister(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("/bookmark/all")
    suspend fun getAllBookmark(@Header("local_investor_token") investorToken: String): Response<BookmarkResponse>

    @GET("/campaign/all")
    suspend fun getCampaign(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sortBy") sortBy: String,
        @Query("sortType") sortType: String
    ): Response<CampaignResponse>


    @GET("/campaign/all")
    suspend fun getDonasiCepat(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sortBy") sortBy: String,
        @Query("sortType") sortType: String
    ): Response<DonasiCepatResponse>

    @GET("/campaign/detail/{ID}")
    suspend fun getCampaignById(
        @Path("ID") id: Int
    ): Response<CampaignDetailResponse>

    @GET("/reward/all")
    suspend fun getRewardByCampaignId(
        @Query("campaignId") campaignId: Int
    ) : Response<RewardResponse>

    @Headers("Content-Type:application/json")
    @POST("/donation/add")
    suspend fun addDonation(
        @Body request: DonasiRequest,
        @Header("Authorization") investor_token: String
    ) : Response<DonasiResponse>

    @GET("/address/all")
    suspend fun getAllAddress(
        @Header("Authorization") investor_token: String
    ): Response<AddressResponse>

    @Headers("Content-Type:application/json")
    @PUT("/donation/update")
    suspend fun updateDonation(
        @Body request: UpdateDonasiRequest,
        @Header("Authorization") investor_token: String
    ) : Response<UpdateDonasiResponse>

    @Headers("Content-Type:application/json")
    @POST("/address/add")
    suspend fun addAddress(
        @Body request: AddAddressRequest,
        @Header("Authorization") investor_token: String
    ): Response<AddAddressResponse>

    @PUT("/address/main/{ID}")
    suspend fun setMainAdrres(
        @Path("ID") id: Int,
        @Header("Authorization") investor_token: String
    ): Response<SetMainAddressResponse>

    @GET("/address/detail/{ID}")
    suspend fun getDetailAddress(
        @Path("ID") id: Int,
        @Header("Authorization") investor_token: String
    ): Response<DetailAddressResponse>

    @GET("/donation/all")
    suspend fun getMessages(
        @Query("campaignId") id: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sortBy") sortBy: String,
        @Query("sortType") sortType: String
    ): Response<DonationsResponse>

    @GET("/faq/all")
    suspend fun getFaq(
        @Query("campaignId") id: Int,
    ): Response<FaqResponse>

    @GET("/reward/all")
    suspend fun getRewardByCampaign(
        @Query("campaignId") id: Int,
    ): Response<RewardByCampaignResponse>

    @GET("/campaign/all")
    suspend fun getRecommendCampaign(
        @Query("categoryId") id: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sortBy") sortBy: String,
        @Query("sortType") sortType: String
    ): Response<CampaignByCategoryResponse>

    @GET("/history/all")
    suspend fun getHistory(
        @Query("campaignId") id: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sortBy") sortBy: String,
        @Query("sortType") sortType: String
    ): Response<HistoryResponse>

    @GET("/donation/all/user")
    suspend fun getDonationsByUserId(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sortBy") sortBy: String,
        @Query("sortType") sortType: String,
        @Header("Authorization") investor_token: String
    ): Response<getDonationsByUserIdResponse>


    @Headers("Content-Type: application/json")
    @PUT("/user/update")
    suspend fun updateProfile(
        @Header("Authorization") investor_token: String,
        @Body request: UpdateProfileRequest
    ): Response<UpdateProfileResponse>

    @GET("/user/detail")
    suspend fun getUserDetail(
        @Header("Authorization") investor_token: String
    ): Response<UserDetailResponse>

    @Headers("Content-Type:application/json")
    @PUT("/user/update-password")
    suspend fun updatePassword(
        @Body request: UpdatePasswordRequest,
        @Header("Authorization") investor_token: String
    ):Response<UpdatePasswordResponse>

    @GET("/gallery/all")
    suspend fun getAllGallery(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sortBy") sortBy: String,
        @Query("sortType") sortType: String,
    ): Response<AllGalleryResponse>

    companion object {
        fun getInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
//                .baseUrl("https://asakarya-testing.herokuapp.com/")
                .baseUrl("https://asakarya-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }
    }
}

