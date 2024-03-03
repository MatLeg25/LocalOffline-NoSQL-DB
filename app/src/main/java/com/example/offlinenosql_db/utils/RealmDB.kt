package com.example.offlinenosql_db.utils

import com.example.offlinenosql_db.data.local.entities.DetailRm
import com.example.offlinenosql_db.data.local.entities.AddressRm
import com.example.offlinenosql_db.data.local.entities.RealmPerson
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmDB {
    val config = RealmConfiguration.create(schema = setOf(RealmPerson::class, AddressRm::class, DetailRm::class))
    val realm: Realm = Realm.open(config)
}