package explorer.city.com.cityexplorer.network

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor: HttpLoggingInterceptor.Logger {

    val TAG = "LoggingInterceptor"

    override fun log(message: String) {
        Log.d(TAG, message)
    }
}