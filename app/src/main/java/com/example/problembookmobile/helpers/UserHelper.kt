package com.example.problembookmobile.helpers

import com.example.problembookmobile.models.Users
import io.paperdb.Paper

class UserHelper(val user : Users) {

    companion object{

        fun rememberUser(user : Users){
            Paper.book("user").write("user", user)
        }

        fun getUser() : Users?{
            return Paper.book("user").read("user")
        }

        fun exitUser(){
            Paper.book("user").destroy()
        }

    }

}