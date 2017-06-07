package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class Embedded(
        @Json(name = "city:search-results")
        val searchResults: List<SearchItem>
)