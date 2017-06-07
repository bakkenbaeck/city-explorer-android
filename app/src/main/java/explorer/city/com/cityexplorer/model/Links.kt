package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class Links(
        @Json(name = "city:item")
        val link: Link
)