package com.sayeyes.eyesapp

import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.sayeyes.eyesapp.adapters.SuggestionsAdapter
import com.sayeyes.eyesapp.adapters.TheyAddedYouAdapter
import com.sayeyes.eyesapp.model.User
import com.sayeyes.eyesapp.presenters.HomePresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import android.support.v7.widget.DividerItemDecoration
import android.text.InputFilter
import android.text.InputType
import android.view.View
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity(), HomeContract.View, ItemClickListener {


    private val presenter : HomePresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.prepareData(applicationContext)

        redcode.clearComposingText()
        purplecode.clearComposingText()
        bluecode.clearComposingText()
        yellowcode.clearComposingText()

        redcode.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(1))
        purplecode.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(1))
        bluecode.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(1))
        yellowcode.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(1))

    }

    override fun displayData(theyAddedYouUsers: List<User>, suggestedUsers: List<User>) {
        they_added_you_recycler_view.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        they_added_you_recycler_view.adapter = TheyAddedYouAdapter(applicationContext,theyAddedYouUsers,this)
        LinearSnapHelper().attachToRecyclerView(they_added_you_recycler_view)
        they_added_you_recycler_view.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.HORIZONTAL))
        they_added_you_recycler_view.isNestedScrollingEnabled = false;

        suggested_users_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        suggested_users_recycler_view.adapter = SuggestionsAdapter(applicationContext,suggestedUsers)
        suggested_users_recycler_view.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
        suggested_users_recycler_view.isNestedScrollingEnabled = false;
    }

    override fun updateTheyAddedYou(clear : Boolean) {
        if(clear){
            they_added_you_label.visibility = View.GONE
            they_added_you_recycler_view.adapter = null
        } else {
            they_added_you_recycler_view.adapter?.notifyDataSetChanged()
        }
    }


    override fun itemClicked(position: Int) {
        presenter.removeUserFromPosition(position)
    }

}
