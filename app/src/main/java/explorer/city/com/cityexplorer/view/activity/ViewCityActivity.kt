package explorer.city.com.cityexplorer.view.activity

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.CityInfo
import explorer.city.com.cityexplorer.viewModel.ViewCityViewModel
import kotlinx.android.synthetic.main.activity_view_city.*

class ViewCityActivity : LifecycleActivity() {

    companion object {
        val CITY_LINK = "CITY_LINK"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_city)
        initClickListeners()
        getCityInfo()
    }

    private fun initClickListeners() {
        backArrow.setOnClickListener { finish() }
    }

    private fun getCityInfo() {
        val model: ViewCityViewModel = ViewModelProviders.of(this).get(ViewCityViewModel::class.java)
        model.fetchCityInfo(getCityLink())
                .observe(this, Observer { updateCityUi(it) })
    }

    private fun updateCityUi(cityInfo: CityInfo?) {
        toolbarTitle.text = cityInfo?.name
        cityName.text = cityInfo?.fullName
        continent.text = getString(R.string.population, cityInfo?.population ?: 0)
    }

    private fun getCityLink(): String {
        return intent.getStringExtra(CITY_LINK)
    }
}
