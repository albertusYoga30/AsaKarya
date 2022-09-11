package app.binar.synergy.android.asakarya.data.api.campaign

import com.squareup.moshi.Json

data class ResponseItem<T>(
    @field:Json(name = "results") val results: List<T>
)
