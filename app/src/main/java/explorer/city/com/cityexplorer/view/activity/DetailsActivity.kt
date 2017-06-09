package explorer.city.com.cityexplorer.view.activity

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.CityDetail
import explorer.city.com.cityexplorer.view.adapter.DetailsAdapter
import explorer.city.com.cityexplorer.viewModel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : LifecycleActivity() {

    companion object {
        val CATEGORY_NAME = "CATEGORY_NAME"
        val URL = "URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        initClickListeners()
        initToolbar()
        initRecyclerView()
        initObservers()
        getDetails()
    }

    private fun initClickListeners() {
        backArrow.setOnClickListener { finish() }
    }

    private fun initToolbar() {
        toolbarTitle.text = getCategoryName()
    }

    private fun initRecyclerView() {
        detailsList.layoutManager = LinearLayoutManager(this)
        detailsList.adapter = DetailsAdapter()
    }

    private fun initObservers() {
        val model = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        model.liveDetails.observe(this, Observer { updateDetailsUi(it) })
    }

    private fun updateDetailsUi(cityDetails: List<CityDetail>?) {
        val adapter: DetailsAdapter = detailsList.adapter as DetailsAdapter
        adapter.setItems(cityDetails)
    }

    private fun getDetails() {
        val model = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
        model.fetchDetails(getUrl(), getCategoryName())
    }

    private fun getUrl() = intent.getStringExtra(URL)

    private fun getCategoryName() = intent.getStringExtra(CATEGORY_NAME)
}
