package explorer.city.com.cityexplorer.View.Activity

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.jakewharton.rxbinding2.widget.RxTextView
import explorer.city.com.cityexplorer.Model.SearchItem
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.View.Adapter.SearchAdapter
import explorer.city.com.cityexplorer.ViewModel.MainViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : LifecycleActivity() {

    companion object {
        val TAG: String = "MainActivity"
    }

    val subscriptions: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initDataObserver()
        initSearchView()
    }

    private fun initRecyclerView() {
        searchList.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        searchList.adapter = SearchAdapter()
    }

    private fun initDataObserver() {
        val model: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        model
                .getSearchResult()
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
                        { Log.e(TAG, "Error -> $it")}
                )

        subscriptions.add(sub)
    }

    private fun updateUi(cities: MutableList<SearchItem>?) {
        val adapter: SearchAdapter = searchList.adapter as SearchAdapter
        adapter.addItems(cities)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}
