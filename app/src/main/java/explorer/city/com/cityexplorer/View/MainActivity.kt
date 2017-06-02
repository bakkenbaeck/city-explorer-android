package explorer.city.com.cityexplorer.View

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import explorer.city.com.cityexplorer.R

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
