package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class EmbeddedUrbanArea(@Json(name = "city:urban_area") val urbanArea: UrbanArea?)