package com.sayeyes.eyesapp.data

import android.content.Context
import com.sayeyes.eyesapp.model.User

interface UserDataProvider {
    fun getUsers(context : Context, count : Int) : MutableList<User>
}