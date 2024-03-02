package com.example.offlinenosql_db.data.local.entities


import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class RealmPerson() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var address: Address? = null

    fun getDisplayText(): String {
        return "$name | ${address?.street ?: ""} | ${address?.details?.description ?: ""}"
    }
}

class Address : RealmObject {
    var street: String = "Foo"
    var details: Details? = null
}

class Details : RealmObject {
    var description: String = ""
    var isValid: Boolean = true
}