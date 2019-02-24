package com.updown.samplemvvmapp.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.updown.samplemvvmapp.R
import com.updown.samplemvvmapp.databinding.FragmentProjectDetailsBinding
import com.updown.samplemvvmapp.model.Project
import com.updown.samplemvvmapp.viewmodel.ProjectViewModel

class ProjectFragment : Fragment() {
    private lateinit var binding: FragmentProjectDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)

        // Create and set the adapter for the RecyclerView.
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ProjectViewModel.Factory(
                activity!!.application, arguments!!.getString(KEY_PROJECT_ID))

        val viewModel = ViewModelProviders.of(this, factory)
                .get(ProjectViewModel::class.java)

        binding.setProjectViewModel(viewModel)
        binding.setIsLoading(true)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectViewModel) {
        // Observe project data
        viewModel.observableProject.observe(this, Observer<Project> { project ->
            if (project != null) {
                binding.setIsLoading(false)
                viewModel.setProject(project)
            }
        })
    }

    companion object {
        private val KEY_PROJECT_ID = "project_id"

        /** Creates project fragment for specific project ID  */
        fun forProject(projectID: String?): ProjectFragment {
            val fragment = ProjectFragment()
            val args = Bundle()

            args.putString(KEY_PROJECT_ID, projectID)
            fragment.arguments = args

            return fragment
        }
    }
}