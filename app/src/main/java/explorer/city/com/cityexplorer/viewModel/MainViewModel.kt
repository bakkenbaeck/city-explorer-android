package explorer.city.com.cityexplorer.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import explorer.city.com.cityexplorer.model.SearchItem
import explorer.city.com.cityexplorer.network.TeleportService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {

    private val TAG = "MainViewModel"

    private val subscriptions by lazy { CompositeDisposable() }
    val liveSearchResult by lazy { MutableLiveData<List<SearchItem>>() }

    fun search(query: String): MutableLiveData<List<SearchItem>> {
        searchCities(query)
        return liveSearchResult
    }

    private fun searchCities(query: String) {
        val sub = TeleportService()
                .telegramInterface
                .searchCities(query)
                .map { it?.embedded }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { liveSearchResult.value = it?.searchResults },
                        { Log.e(TAG, "Error $it")}
                )

        subscriptions.add(sub)
    }
}