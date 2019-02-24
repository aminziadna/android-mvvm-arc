package com.updown.samplemvvmapp.view.callback

import com.updown.samplemvvmapp.model.Project


interface ProjectClickCallback {
    abstract fun onClick(project: Project)
}