package com.sayeyes.eyesapp.presenters

import android.content.Context
import com.sayeyes.eyesapp.HomeContract
import com.sayeyes.eyesapp.model.Repository
import com.sayeyes.eyesapp.model.User

class HomePresenter(var view : HomeContract.View, var repo : Repository) : HomeContract.Presenter {

    lateinit var theyAddedYouUsers : MutableList<User>
    lateinit var suggestMembers : MutableList<User>

    override fun prepareData(context: Context) {
        theyAddedYouUsers = repo.provideUsers(context,5)
        suggestMembers = repo.provideUsers(context,10)
        view.displayData(theyAddedYouUsers,suggestMembers)
    }

    override fun removeUserFromPosition(position: Int) {
        theyAddedYouUsers.removeAt(position)
        view.updateTheyAddedYou(theyAddedYouUsers.count() == 0)
    }
}