package com.updown.samplemvvmapp.view.ui

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.updown.samplemvvmapp.R
import com.updown.samplemvvmapp.databinding.FragmentProjectListBinding
import com.updown.samplemvvmapp.model.Project
import com.updown.samplemvvmapp.view.adapter.ProjectAdapter
import com.updown.samplemvvmapp.view.callback.ProjectClickCallback
import com.updown.samplemvvmapp.viewmodel.ProjectListViewModel

class ProjectListFragment : Fragment() {
    private var projectAdapter: ProjectAdapter? = null
    private lateinit var binding: FragmentProjectListBinding

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(project)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)

        projectAdapter = ProjectAdapter(projectClickCallback)
        binding.projectList.adapter = projectAdapter
        binding.isLoading = true

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        // Update the list when the data changes
        viewModel.projectListObservable.observe(this, Observer<List<Project>> { projects ->
            if (projects != null) {
                binding.isLoading = false
                projectAdapter?.projectList = projects
            }
        })
    }

    companion object {
        val TAG = "ProjectListFragment"
    }
}