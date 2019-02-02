package com.sayeyes.eyesapp.data

import android.content.Context
import com.sayeyes.eyesapp.model.User

class UserDataProviderImpl : UserDataProvider {

    override fun getUsers(context: Context, count : Int): MutableList<User> {
        val userList = mutableListOf<User>()
        var i = 0
        context.assets.open("users.txt").bufferedReader().useLines { lines ->
            lines.forEach {
                if(i < count){
                    userList.add(createPaintingObject(it))
                    i++
                } else {
                    return userList
                }
            }
        }

        return userList
    }

    private fun createPaintingObject(userValue: String): User {
        val userString = userValue
        val userStringArray = userString.split(",")

        val user = User(
            userStringArray[0],
            userStringArray[1],
            userStringArray[2],
            userStringArray[3],
            userStringArray[4].toBoolean(),
            userStringArray[5].toBoolean(),
            userStringArray[6].toBoolean(),
            userStringArray[7].toBoolean(),
            userStringArray[8].toBoolean(),
            userStringArray[9].toBoolean(),
            userStringArray[10]
            )

        return user
    }

}