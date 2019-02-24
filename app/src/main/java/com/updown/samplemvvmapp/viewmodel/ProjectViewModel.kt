package com.updown.samplemvvmapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.databinding.ObservableField
import com.updown.samplemvvmapp.model.Project
import com.updown.samplemvvmapp.network.GitHubApi

class ProjectViewModel(application: Application,
                       private val projectID: String) : AndroidViewModel(application) {
    val observableProject: LiveData<Project>

    var project = ObservableField<Project>()

    init {

        observableProject = GitHubApi.getInstance().getProjectDetails("Google", projectID)
    }

    fun setProject(project: Project) {
        this.project.set(project)
    }

    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    class Factory(private val application: Application, private val projectID: String) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ProjectViewModel(application, projectID) as T
        }
    }
}