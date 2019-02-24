package com.updown.samplemvvmapp.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.updown.samplemvvmapp.model.Project
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubApi private constructor() {

    private var gitHubService: GitHubService

    init {
        gitHubService = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
    }

    fun getProjectList(userId: String): LiveData<List<Project>> {
        val data = MutableLiveData<List<Project>>()

        gitHubService.getProjectList(userId).enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }

    fun getProjectDetails(userID: String, projectName: String): LiveData<Project> {
        val data = MutableLiveData<Project>()

        gitHubService.getProjectDetails(userID, projectName).enqueue(object : Callback<Project> {
            override fun onResponse(call: Call<Project>, response: Response<Project>) {
//                simulateDelay()
                data.value = response.body()
            }

            override fun onFailure(call: Call<Project>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }

    companion object {

        private val mInstance: GitHubApi = GitHubApi()

        @JvmStatic
        @Synchronized
        fun getInstance(): GitHubApi {
            return mInstance
        }


    }
}