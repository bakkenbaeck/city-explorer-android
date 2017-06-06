package explorer.city.com.cityexplorer.Network

import explorer.city.com.cityexplorer.Model.CitySearch
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeleportInterface {

    @GET("cities/")
    fun searchCities(@Query("search") query: String): Single<Response<CitySearch>>
}