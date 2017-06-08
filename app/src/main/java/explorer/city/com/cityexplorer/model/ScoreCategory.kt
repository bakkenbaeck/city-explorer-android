package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class ScoreCategory(
        val color: String,
        val name: String,
        @Json(name = "score_out_of_10")
        val score: Double
)