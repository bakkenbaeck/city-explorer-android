package explorer.city.com.cityexplorer.network

import explorer.city.com.cityexplorer.model.CitySearch
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TeleportInterface {

    @GET("cities/")
    fun searchCities(@Query("search") query: String): Single<CitySearch>
}