package explorer.city.com.cityexplorer.network

import com.squareup.moshi.Moshi
import explorer.city.com.cityexplorer.BaseApplication
import explorer.city.com.cityexplorer.R
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class TeleportService {

    val telegramInterface: TeleportInterface by lazy { initClient() }

    private fun initClient(): TeleportInterface {
        val client = OkHttpClient()
                .newBuilder()
                .addInterceptor(addLoggingInterceptor())
        val moshi = Moshi.Builder().build()
        val rxAdapter = RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io())
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseApplication.instance.getString(R.string.base_url))
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(rxAdapter)
                .client(client.build())
                .build()

        return retrofit.create(TeleportInterface::class.java)
    }

    private fun addLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(LoggingInterceptor())
    }
}
