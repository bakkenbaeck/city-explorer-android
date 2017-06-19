package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class EmbeddedImage(@Json(name = "ua:images") val photos: Photos?)