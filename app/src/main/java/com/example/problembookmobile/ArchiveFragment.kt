package com.example.problembookmobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.problembookmobile.helpers.UserHelper
import com.example.problembookmobile.models.Projects
import com.example.problembookmobile.services.ApiInterface
import com.example.problembookmobile.services.ServiceBuilder
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Path


class ArchiveFragment : Fragment() {

    lateinit var archiveList : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArchiveList()

    }

    private fun getArchiveList() {
        val item = UserHelper.getUser()
        val serviceBuilder = ServiceBuilder.buildService(ApiInterface::class.java)
        val requestCall = serviceBuilder.getArchiveByToken(item!!.mobileToken)
        requestCall.enqueue(object : Callback<ArrayList<Projects>> {
            override fun onResponse(
                call: Call<ArrayList<Projects>>,
                response: Response<ArrayList<Projects>>
            ) {
                archiveList.layoutManager = LinearLayoutManager(context)
                archiveList.setHasFixedSize(true)
                archiveList.adapter = ProjectAdapter(requireContext(), response.body()!!){
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    val g = Gson()
                    intent.putExtra("PROJECT", g.toJson(it))
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<ArrayList<Projects>>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val archive = inflater.inflate(R.layout.fragment_archive, container, false)
        archiveList = archive.findViewById(R.id.archive_list)
        return archive
    }

}