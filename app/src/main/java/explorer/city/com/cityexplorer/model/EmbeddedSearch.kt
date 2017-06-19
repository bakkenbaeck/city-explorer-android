package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class EmbeddedSearch(@Json(name = "city:item") val cityInfo: CityInfo)