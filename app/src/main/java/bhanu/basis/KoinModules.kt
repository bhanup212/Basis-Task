package bhanu.basis

import bhanu.basis.network.ApiClient
import bhanu.basis.viewmodel.MainViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by: Bhanu Prakash
 * Created on:20:19, 11,November,2019
 */

val viewModelModules = module {
    viewModel { MainViewModel(get()) }
}

val apiModule = module {
    fun provideLoanSimpleApi(retrofit: Retrofit): ApiClient = retrofit.create(ApiClient::class.java)
    single { provideLoanSimpleApi(get()) }
}

val retrofitModule = module {
    fun provideRetrofit(httpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }

    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    fun provideGson() = GsonBuilder()
        .setLenient()
        .create()

    single { provideRetrofit(get()) }
    single { provideHttpClient() }
}