package explorer.city.com.cityexplorer.view.activity

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.jakewharton.rxbinding2.widget.RxTextView
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.SearchItem
import explorer.city.com.cityexplorer.view.listener.OnItemClickListener
import explorer.city.com.cityexplorer.view.adapter.SearchAdapter
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
        initRecyclerView()
        initSearchView()
    }

    private fun initDataObserver() {
        val model: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        model.liveSearchResult
                .observe(this, Observer { updateUi(it) })
    }

    private fun initRecyclerView() {
        searchList.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        val adapter = SearchAdapter(OnItemClickListener<String> { gotoViewCityActivity(it) })
        searchList.adapter = adapter
    }

    private fun gotoViewCityActivity(cityLink: String) {
        val intent: Intent = Intent(this, ViewCityActivity::class.java)
                .putExtra(ViewCityActivity.CITY_URL, cityLink)
        startActivity(intent)
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
                        { Log.e(TAG, "Error -> $it")}
                )

        subscriptions.add(sub)
    }

    private fun updateUi(cities: List<SearchItem>?) {
        val adapter: SearchAdapter = searchList.adapter as SearchAdapter
        adapter.addItems(cities)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}
