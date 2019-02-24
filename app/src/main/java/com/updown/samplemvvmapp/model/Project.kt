package com.updown.samplemvvmapp.model

import java.util.*

class Project {
    var id: Long = 0
    var name: String? = null
    var description: String? = null
    var created_at: Date? = null
    var updated_at: Date? = null
    var git_url: String? = null
    var clone_url: String? = null
    var language: String? = null
    var open_issues: Int = 0
    var watchers: Int = 0

    constructor() {}

    constructor(name: String) {
        this.name = name
    }
}