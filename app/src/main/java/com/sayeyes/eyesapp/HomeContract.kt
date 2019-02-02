package com.sayeyes.eyesapp

import android.content.Context
import com.sayeyes.eyesapp.model.User

interface HomeContract {
    interface View {
        fun displayData(theyAddedYouUsers : List<User>,suggestedUsers : List<User>)
        fun updateTheyAddedYou(clear : Boolean)
    }

    interface Presenter {
        fun prepareData(context: Context)
        fun removeUserFromPosition(position: Int)
    }
}