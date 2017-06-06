package explorer.city.com.cityexplorer.Model

import com.squareup.moshi.Json

data class Embedded(
        @Json(name = "city:search-results")
        val searchResults: MutableList<SearchItem>
)