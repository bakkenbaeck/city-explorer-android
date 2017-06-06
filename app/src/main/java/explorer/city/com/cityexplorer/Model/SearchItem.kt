package explorer.city.com.cityexplorer.Model

import com.squareup.moshi.Json

data class SearchItem(
        @Json(name = "_links")
        val links: Links,
        @Json(name = "matching_alternate_names")
        val alternateNames: List<AlternateName>,
        @Json(name  = "matching_full_name")
        val fullName: String
)