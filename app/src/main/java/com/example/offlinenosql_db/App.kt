package com.example.offlinenosql_db

import android.app.Application
import com.example.offlinenosql_db.utils.ObjectBox

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}