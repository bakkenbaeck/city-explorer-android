package explorer.city.com.cityexplorer.network

import explorer.city.com.cityexplorer.model.CityInfo
import explorer.city.com.cityexplorer.model.CitySearch
import explorer.city.com.cityexplorer.model.Photos
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface TeleportInterface {

    @GET("cities/")
    fun searchCities(@Query("search") query: String): Single<CitySearch>

    @GET
    fun getCityInfo(@Url url: String): Single<CityInfo>

    @GET
    fun getImage(@Url url: String): Single<Photos>
}