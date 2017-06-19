package explorer.city.com.cityexplorer.view.activity

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.text.Html
import explorer.city.com.cityexplorer.R
import explorer.city.com.cityexplorer.model.CityInfo
import explorer.city.com.cityexplorer.model.CityScore
import explorer.city.com.cityexplorer.model.Photo
import explorer.city.com.cityexplorer.model.Photos
import explorer.city.com.cityexplorer.util.load
import explorer.city.com.cityexplorer.view.listener.OnItemClickListener
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
        toolbar.setNavigationOnClickListener { finish() }
        categoriesView.setOnItemClickListener(OnItemClickListener<String> { startDetailActivity(it) })
    }

    private fun startDetailActivity(category: String) {
        val model: ViewCityViewModel = ViewModelProviders.of(this).get(ViewCityViewModel::class.java)
        val detailsUrl: String? = model.liveDetails.value?.links?.details?.href
        val intent = Intent(this, DetailsActivity::class.java)
                .putExtra(DetailsActivity.CATEGORY_NAME, category)
                .putExtra(DetailsActivity.URL, detailsUrl)
        startActivity(intent)
    }

    private fun initDataObservers() {
        val model: ViewCityViewModel = ViewModelProviders.of(this).get(ViewCityViewModel::class.java)
        model.liveCityInfo.observe(this, Observer { updateCityUi(it) })
        model.liveCityScore.observe(this, Observer { updateScoreUi(it) })
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

    private fun updateScoreUi(cityScore: CityScore?) {
        val score = String.format("%.2f", cityScore?.cityScore ?: 0)
        teleportScore.text = getString(R.string.teleport_score, score)
        summary.text = Html.fromHtml(cityScore?.summary)
        categoriesView.addItems(cityScore?.categories)
    }

    private fun updateImageUi(photos: Photos?) {
        val photo: Photo? = photos?.photos?.get(0)
        photo?.getImage()?.let {
            cityImage.load(it)
        }
    }
}
