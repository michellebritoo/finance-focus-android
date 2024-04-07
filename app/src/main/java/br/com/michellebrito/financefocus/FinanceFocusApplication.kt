package br.com.michellebrito.financefocus

import android.app.Application
import br.com.michellebrito.financefocus.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FinanceFocusApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FinanceFocusApplication)
            modules(appModule)
        }
    }
}