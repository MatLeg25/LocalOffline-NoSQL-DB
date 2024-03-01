package com.example.offlinenosql_db.utils

import com.example.offlinenosql_db.data.local.entities.RealmItem
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmDB {
    val config = RealmConfiguration.create(schema = setOf(RealmItem::class))
    val realm: Realm = Realm.open(config)
}