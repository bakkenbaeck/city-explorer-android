package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class CitySearch(
        @Json(name = "_embedded")
        val embedded: Embedded
) {
        companion object {
                val EMBEDDED_SEARCH = "city:search-results/city:item/{city:urban_area, city:urban_area/ua:images}"
        }
}