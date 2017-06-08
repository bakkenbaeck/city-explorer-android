package explorer.city.com.cityexplorer.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import explorer.city.com.cityexplorer.model.CityDetail
import explorer.city.com.cityexplorer.network.TeleportService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailsViewModel: ViewModel() {

    private val TAG = "DetailsViewModel"
    val liveDetails by lazy { MutableLiveData<List<CityDetail>>() }
    val subscriptions by lazy { CompositeDisposable() }

    fun fetchDetails(url: String, categoryName: String) {
        if (shouldFetch()) {
            fetchDetailsFromUrl(url, categoryName)
        }
    }

    private fun shouldFetch(): Boolean = liveDetails.value == null

    private fun fetchDetailsFromUrl(url: String, categoryName: String) {
        val sub = TeleportService
                .telegramInterface
                .getCityDetails(url)
                .map { it.categories }
                .toObservable()
                .flatMapIterable { it }
                .filter { it.label == categoryName }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { liveDetails.value = it.data },
                        { Log.e(TAG, "Error -> $it") }
                )

        subscriptions.add(sub)
    }
}