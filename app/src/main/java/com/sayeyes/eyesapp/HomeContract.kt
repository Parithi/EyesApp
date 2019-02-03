package com.sayeyes.eyesapp

import android.content.Context
import com.sayeyes.eyesapp.model.User

enum class HomeTags {
    SUGGESTED_USERS,
    THEY_ADDED_YOU
}

interface HomeContract {
    interface View {
        fun displayData(theyAddedYouUsers : List<User>,suggestedUsers : List<User>)
        fun updateTheyAddedYou(clear : Boolean)
        fun updateSuggestedUsers(clear : Boolean)
    }

    interface Presenter {
        fun prepareData(context: Context)
        fun removeUserFromPosition(tag : HomeTags, position: Int)
    }
}