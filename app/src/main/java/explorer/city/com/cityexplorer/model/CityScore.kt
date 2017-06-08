package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class CityScore(
        val categories: List<ScoreCategory>,
        val summary: String,
        @Json(name = "teleport_city_score")
        val cityScore: Double
)