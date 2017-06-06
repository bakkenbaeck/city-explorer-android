package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class CitySearch(
        @Json(name = "_embedded")
        val embedded: Embedded
)