package com.sayeyes.eyesapp.model

import android.content.Context
import com.sayeyes.eyesapp.data.UserDataProvider

class Repository(var dataProvider : UserDataProvider) {

    fun provideUsers(context : Context,count : Int) : MutableList<User>{
        return dataProvider.getUsers(context,count)
    }

}