package explorer.city.com.cityexplorer.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import explorer.city.com.cityexplorer.Model.SearchItem
import explorer.city.com.cityexplorer.Network.TeleportService
import explorer.city.com.cityexplorer.View.Activity.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel() {

    private val subscriptions by lazy { CompositeDisposable() }
    private val liveSearchResult by lazy { MutableLiveData<MutableList<SearchItem>>() }

    fun search(query: String): MutableLiveData<MutableList<SearchItem>> {
        searchCities(query)
        return liveSearchResult
    }

    private fun searchCities(query: String) {
        val sub = TeleportService
                .getApi()
                .searchCities(query)
                .map { it.body()?.embedded }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { liveSearchResult.value = it?.searchResults },
                        { Log.e(MainActivity.TAG, "Error $it")}
                )

        subscriptions.add(sub)
    }

    fun getSearchResult(): MutableLiveData<MutableList<SearchItem>> {
        return liveSearchResult
    }
}