package explorer.city.com.cityexplorer.model

import com.squareup.moshi.Json

data class EmbeddedSearch(@Json(name = "city:item") val cityInfo: CityInfo) {
    fun getImage(): String? = cityInfo.embeddedUrbanArea?.urbanArea?.embeddedImage?.photos?.photos?.get(0)?.image?.web
}