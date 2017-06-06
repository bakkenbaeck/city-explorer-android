package explorer.city.com.cityexplorer.view.activity

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import com.jakewharton.rxbinding2.widget.RxTextView
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.SearchItem
import explorer.city.com.cityexplorer.view.MainActivity
import explorer.city.com.cityexplorer.viewModel.MainViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : LifecycleActivity() {

    private val TAG: String = "MainActivity"
    private val subscriptions: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDataObserver()
        initSearchView()
    }

    private fun initDataObserver() {
        val model: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        model.liveSearchResult
                .observe(this, Observer { updateUi(it) })
    }

    private fun initSearchView() {
        val model: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val sub = RxTextView
                .textChanges(searchInput)
                .skip(1)
                .debounce(400, TimeUnit.MILLISECONDS)
                .map { it.toString() }
                .subscribe(
                        { model.search(it) },
                        { Log.e(MainActivity.TAG, "Error -> $it")}
                )

        subscriptions.add(sub)
    }

    private fun updateUi(cities: List<SearchItem>?) {
        Log.d(MainActivity.TAG, "updateUi -> ${cities?.size}")
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}
