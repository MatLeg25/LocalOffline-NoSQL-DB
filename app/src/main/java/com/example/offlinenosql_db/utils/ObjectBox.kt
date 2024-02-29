package com.example.offlinenosql_db.utils

import android.content.Context
import com.example.offlinenosql_db.data.local.entities.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {
    lateinit var store: BoxStore
        private set

    fun init(context: Context) {
        store = MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .inMemory("test-db") // use an in-memory database for testing or caching, that does not create any files
                .build()
    }
}