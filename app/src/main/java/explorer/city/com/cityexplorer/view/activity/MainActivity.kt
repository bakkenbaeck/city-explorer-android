package explorer.city.com.cityexplorer.view.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import explorer.city.com.cityexplorer.R

class MainActivity : LifecycleActivity() {

    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
