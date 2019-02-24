package com.updown.samplemvvmapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.updown.samplemvvmapp.model.Project
import com.updown.samplemvvmapp.network.GitHubApi


class ProjectListViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    val projectListObservable: LiveData<List<Project>>

    init {
        projectListObservable = GitHubApi.getInstance().getProjectList("Google")
    }
}