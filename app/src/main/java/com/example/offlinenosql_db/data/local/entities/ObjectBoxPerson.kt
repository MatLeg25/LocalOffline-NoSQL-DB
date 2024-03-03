package com.example.offlinenosql_db.data.local.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import io.realm.kotlin.ext.toRealmDictionary
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmDictionary
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

/**
 * ObjectBox hasn't support embedded objects yet (03.2024)
 * [https://github.com/objectbox/objectbox-java/issues/456]
 */


@Entity
data class ObjectBoxPerson(
        @Id
        var id: Long = 0,
        var name: String? = null,
) {
        var address: ToOne<AddressOB>? = null
}

@Entity
class AddressOB(
        @Id
        var id: Long = 0,
        var street: String = "Foo"
)
