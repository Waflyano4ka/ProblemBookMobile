package com.example.problembookmobile.services

import com.example.problembookmobile.models.Projects
import com.example.problembookmobile.models.Tasks
import com.example.problembookmobile.models.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("authorization/{token}")
    fun getUserByToken(@Path("token") token : String?) : Call<ArrayList<Users>>

    @GET("projects/{token}")
    fun getProjectsByToken(@Path("token") token: String) : Call<ArrayList<Projects>>

    @GET("archive/{token}")
    fun getArchiveByToken(@Path("token") token: String) : Call<ArrayList<Projects>>

    @GET("tasks/{idProject}/{token}")
    fun getTasks(@Path("idProject") idProject : Long, @Path("token") token: String) : Call<ArrayList<Tasks>>


}