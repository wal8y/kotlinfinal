package com.iau.afinal

import android.app.Application
import com.iau.afinal.data.AppContainer
import com.iau.afinal.data.AppDataContainer

class HotelBrowser : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
