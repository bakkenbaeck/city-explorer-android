package explorer.city.com.cityexplorer

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: BaseApplication
    }

    fun isNetworkAvailable(): Boolean {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}