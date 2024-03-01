package com.example.offlinenosql_db.data.local.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ObjectBoxItem(
        @Id
        var id: Long = 0,
        var name: String? = null,
        var isComplete: Boolean = false
)