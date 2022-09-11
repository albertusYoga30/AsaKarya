package app.binar.synergy.android.asakarya.data.api

import app.binar.synergy.android.asakarya.data.api.image.ImageDataResponse
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ImageAPI {
    companion object {
        fun getInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.imgbb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }
    }

    // post ke url baseUrl/1/upload
    @POST("1/upload")
    @Multipart
    suspend fun uploadImage(
        //10000 ini 10000 second
        @Query("expiration") expiration: Int = 10000,
        @Query("key") key: String = "3ef43fdb06a18016d0dc1d2104f58a23",
        // upload image
        @Part image: MultipartBody.Part
    ): Response<ImageDataResponse>
}
