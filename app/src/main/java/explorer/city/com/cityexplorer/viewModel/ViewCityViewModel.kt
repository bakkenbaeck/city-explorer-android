package explorer.city.com.cityexplorer.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import explorer.city.com.cityexplorer.model.CityInfo
import explorer.city.com.cityexplorer.network.TeleportService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ViewCityViewModel: ViewModel() {

    private val TAG = "ViewCityViewModel"

    private val subscriptions by lazy { CompositeDisposable() }
    val liveCityInfo: MutableLiveData<CityInfo> by lazy { MutableLiveData<CityInfo>() }

    fun fetchCityInfo(cityLink: String ): MutableLiveData<CityInfo> {
        if (shouldFetch()) {
            fetchCityInfoFromUrl(cityLink)
        }
        return liveCityInfo
    }

    private fun shouldFetch(): Boolean {
        return liveCityInfo.value == null
    }

    private fun fetchCityInfoFromUrl(cityLink: String) {
        val sub = TeleportService
                .telegramInterface
                .getCityInfo(cityLink)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { liveCityInfo.value = it },
                        { Log.e(TAG, "Error -> $it") }
                )

        subscriptions.add(sub)
    }
}