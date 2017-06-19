package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class UrbanArea(
        @Json(name = "_links")
        val links: Links,
        @Json(name = "_embedded")
        val embeddedImage: EmbeddedImage?
)