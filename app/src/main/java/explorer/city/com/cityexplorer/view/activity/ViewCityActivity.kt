package explorer.city.com.cityexplorer.view.activity

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.bumptech.glide.Glide
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.CityInfo
import explorer.city.com.cityexplorer.model.Photo
import explorer.city.com.cityexplorer.model.Photos
import explorer.city.com.cityexplorer.viewModel.ViewCityViewModel
import kotlinx.android.synthetic.main.activity_view_city.*

class ViewCityActivity : LifecycleActivity() {

    companion object {
        val CITY_URL = "CITY_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_city)

        initClickListeners()
        initDataObservers()
        getCityInfo()
    }

    private fun initClickListeners() {
        backArrow.setOnClickListener { finish() }
    }

    private fun initDataObservers() {
        val model: ViewCityViewModel = ViewModelProviders.of(this).get(ViewCityViewModel::class.java)
        model.liveCityInfo.observe(this, Observer { updateCityUi(it) })
        model.liveImageInfo.observe(this, Observer { updateImageUi(it) })
    }

    private fun getCityInfo() {
        val model: ViewCityViewModel = ViewModelProviders.of(this).get(ViewCityViewModel::class.java)
        model.fetchCityInfo(getCityInfoUrl())
    }

    private fun getCityInfoUrl(): String {
        return intent.getStringExtra(CITY_URL)
    }

    private fun updateCityUi(cityInfo: CityInfo?) {
        toolbarTitle.text = cityInfo?.name
        cityName.text = cityInfo?.fullName
        continent.text = getString(R.string.population, cityInfo?.population ?: 0)
    }

    private fun updateImageUi(photos: Photos?) {
        val photo: Photo? = photos?.photos?.get(0)
        Glide
                .with(this)
                .load(photo?.image?.web)
                .into(cityImage)
    }
}
