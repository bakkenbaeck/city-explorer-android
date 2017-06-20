package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class CityInfo(
        @Json(name = "_links")
        val links: Links,
        @Json(name = "full_name")
        val fullName: String,
        val name: String,
        val population: Int,
        @Json(name = "_embedded")
        val embeddedUrbanArea: EmbeddedUrbanArea?
)