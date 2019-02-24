package com.updown.samplemvvmapp.model

import java.util.*

class User {
    var id: Long = 0
    var type: String? = null
    var name: String? = null
    var following: Int = 0
    var created_at: Date? = null
    var updated_at: Date? = null
}