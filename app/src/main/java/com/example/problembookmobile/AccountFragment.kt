package com.example.problembookmobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.problembookmobile.helpers.UserHelper
import com.example.problembookmobile.models.Users
import com.example.problembookmobile.services.ApiInterface
import com.example.problembookmobile.services.ServiceBuilder
import com.squareup.picasso.Picasso
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountFragment : Fragment() {

    lateinit var logoutButton : AppCompatButton
    lateinit var updateButton : AppCompatButton
    lateinit var image : ImageView
    lateinit var nameText : TextView
    lateinit var surnameText : TextView
    lateinit var emailText : TextView
    lateinit var regionText : TextView
    lateinit var genderText : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var item : Users? = UserHelper.getUser()

        logoutButton.setOnClickListener {
            UserHelper.exitUser()
            val intent = Intent(context, LaunchActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        updateButton.setOnClickListener {
            updateByToken(item!!.mobileToken)
            item = UserHelper.getUser()
            update(item!!)
        }

        update(item!!)
    }

    private fun updateByToken(token : String){
        val serviceBuilder = ServiceBuilder.buildService(ApiInterface::class.java)
        val requestCall = serviceBuilder.getUserByToken(token)
        requestCall.enqueue(object : Callback<ArrayList<Users>> {
            override fun onResponse(
                call: Call<ArrayList<Users>>,
                response: Response<ArrayList<Users>>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(context, "Информация обновлена", Toast.LENGTH_SHORT).show()
                    UserHelper.rememberUser(response.body()!!.last())
                }else{
                    Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun update(item : Users) {
        Picasso.get().load(item.image).into(image)
        nameText.text = item.name.split(' ')[0]
        surnameText.text = item.name.split(' ')[1]
        emailText.text = item.email
        regionText.text = item.locale
        genderText.text = item.gender
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val accountFragment = inflater.inflate(R.layout.fragment_account, container, false)
        Paper.init(requireContext())
        logoutButton = accountFragment.findViewById(R.id.logoutAccount)
        updateButton = accountFragment.findViewById(R.id.updateAccount)
        image = accountFragment.findViewById(R.id.imageView2)
        nameText = accountFragment.findViewById(R.id.NameText)
        surnameText = accountFragment.findViewById(R.id.SurnameText)
        surnameText = accountFragment.findViewById(R.id.SurnameText)
        emailText = accountFragment.findViewById(R.id.EmailText)
        regionText = accountFragment.findViewById(R.id.RegionText)
        genderText = accountFragment.findViewById(R.id.GenderText)
        return accountFragment
    }
}