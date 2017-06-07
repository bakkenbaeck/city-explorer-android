package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class Links(
        @Json(name = "city:item")
        val link: Link,
        @Json(name = "city:urban_area")
        val urbanArea: Link,
        @Json(name = "city:country")
        val country : Link
)