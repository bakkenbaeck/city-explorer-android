package explorer.city.com.cityexplorer.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import explorer.city.com.cityexplorer.model.CityInfo
import explorer.city.com.cityexplorer.model.Photos
import explorer.city.com.cityexplorer.network.TeleportService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewCityViewModel: ViewModel() {

    private val TAG = "ViewCityViewModel"

    private val subscriptions by lazy { CompositeDisposable() }
    val liveCityInfo: MutableLiveData<CityInfo> by lazy { MutableLiveData<CityInfo>() }
    val liveImageInfo: MutableLiveData<Photos> by lazy { MutableLiveData<Photos>() }

    fun fetchCityInfo(url: String): MutableLiveData<CityInfo> {
        if (shouldFetch()) {
            fetchCityInfoFromUrl(url)
        }
        return liveCityInfo
    }

    private fun shouldFetch(): Boolean {
        return liveCityInfo.value == null
                && liveImageInfo.value == null
    }

    private fun fetchCityInfoFromUrl(url: String) {
        val sub = getCityInfo(url)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { liveCityInfo.value = it }
                .flatMap { getCityInfo(it.links.urbanArea.href) }
                .flatMap { getImageInfo(it.links.image.href) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { liveImageInfo.value = it }
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {  },
                        { Log.e(TAG, "Error -> $it") }
                )

        subscriptions.add(sub)
    }

    private fun getCityInfo(url: String): Single<CityInfo> {
        return TeleportService
                .telegramInterface
                .getCityInfo(url)
    }

    private fun getImageInfo(url: String): Single<Photos> {
        return TeleportService
                .telegramInterface
                .getImage(url)
    }
}