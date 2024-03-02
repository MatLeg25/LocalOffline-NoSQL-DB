package com.example.offlinenosql_db.data.local.entities


import io.realm.kotlin.ext.toRealmDictionary
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmDictionary
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmMap
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class RealmPerson() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var address: Address? = null

    fun getDisplayText(): String {
        return "$name | ${address?.street ?: ""} | ${address?.details?.size ?: "X"} | ${address?.map?.values?.size ?: "Y"} "
    }
}

class Address : RealmObject {
    var street: String = "Foo"
    var details: RealmList<Detail> = emptyList<Detail>().toRealmList()
    var map: RealmDictionary<Detail?> = emptyMap<String, Detail?>().toRealmDictionary()
}

class Detail : RealmObject {
    var description: String = ""
    var isValid: Boolean = true
}