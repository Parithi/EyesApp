package com.sayeyes.eyesapp.presenters

import android.content.Context
import com.sayeyes.eyesapp.HomeContract
import com.sayeyes.eyesapp.HomeTags
import com.sayeyes.eyesapp.model.Repository
import com.sayeyes.eyesapp.model.User

class HomePresenter(var view : HomeContract.View, var repo : Repository) : HomeContract.Presenter {

    lateinit var theyAddedYouUsers : MutableList<User>
    lateinit var suggestedUsers : MutableList<User>

    override fun prepareData(context: Context) {
        theyAddedYouUsers = repo.provideUsers(context,5)
        suggestedUsers = repo.provideUsers(context,10)
        view.displayData(theyAddedYouUsers,suggestedUsers)
    }

    override fun removeUserFromPosition(tag : HomeTags, position: Int) {
        when(tag){
            HomeTags.SUGGESTED_USERS -> {
                suggestedUsers.removeAt(position)
                view.updateSuggestedUsers(suggestedUsers.count() == 0)
            }
            HomeTags.THEY_ADDED_YOU -> {
                theyAddedYouUsers.removeAt(position)
                view.updateTheyAddedYou(theyAddedYouUsers.count() == 0)
            }
        }

    }
}