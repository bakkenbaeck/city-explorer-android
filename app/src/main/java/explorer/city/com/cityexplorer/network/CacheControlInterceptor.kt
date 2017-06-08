package explorer.city.com.cityexplorer.network

import explorer.city.com.cityexplorer.BaseApplication
import okhttp3.Interceptor
import okhttp3.Response

class CacheControlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (BaseApplication.instance.isNetworkAvailable()) {
            val maxAge = 60
            return originalResponse
                    .newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build()
        } else {
            val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
            return originalResponse
                    .newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build()
        }
    }
}

