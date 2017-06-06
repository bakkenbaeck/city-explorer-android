package explorer.city.com.cityexplorer.Model

import com.squareup.moshi.Json

data class CitySearch(
        @Json(name = "_embedded")
        val embedded: Embedded
)