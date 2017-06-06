package explorer.city.com.cityexplorer.Model

import com.squareup.moshi.Json

data class Links(
        @Json(name = "city:item")
        val link: Link
)