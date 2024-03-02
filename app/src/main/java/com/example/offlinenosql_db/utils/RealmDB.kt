package com.example.offlinenosql_db.utils

import com.example.offlinenosql_db.data.local.entities.Details
import com.example.offlinenosql_db.data.local.entities.Address
import com.example.offlinenosql_db.data.local.entities.RealmPerson
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmDB {
    val config = RealmConfiguration.create(schema = setOf(RealmPerson::class, Address::class, Details::class))
    val realm: Realm = Realm.open(config)
}