package explorer.city.com.cityexplorer.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import explorer.city.com.cityexplorer.R

class DetailsActivity : AppCompatActivity() {

    companion object {
        val CATEGORY_NAME = "CATEGORY_NAME"
        val URL = "URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}
