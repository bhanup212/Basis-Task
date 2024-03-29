package bhanu.basis

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by: Bhanu Prakash
 * Created on:20:16, 11,November,2019
 */
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin(){
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(viewModelModules, apiModule, retrofitModule)
        }
    }
}