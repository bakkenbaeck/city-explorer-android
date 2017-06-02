package explorer.city.com.cityexplorer

import android.app.Application

class BaseApplication: Application() {

    lateinit var instance: BaseApplication

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}