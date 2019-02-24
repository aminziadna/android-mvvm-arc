package com.updown.samplemvvmapp.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.updown.samplemvvmapp.R
import com.updown.samplemvvmapp.model.Project

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = ProjectListFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, ProjectListFragment.TAG).commit()
        }
    }

    /** Shows the project detail fragment  */
    fun show(project: Project) {
        val projectFragment = ProjectFragment.forProject(project.name)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container,
                        projectFragment, null).commit()
    }
}
