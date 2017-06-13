package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class CityDetail(
        @Json(name = "float_value")
        val floatValue: Double,
        @Json(name = "int_value")
        val intValue: Double,
        @Json(name = "percent_value")
        val percentValue: Double,
        @Json(name = "string_value")
        val stringValue: String,
        @Json(name = "currency_dollar_value")
        val currencyDollarValue: Double,
        @Json(name = "url_value")
        val urlValue: String,
        val id: String,
        val label: String,
        val type: String
)