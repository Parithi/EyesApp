package com.sayeyes.eyesapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.text.InputFilter
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.sayeyes.eyesapp.adapters.SuggestionsAdapter
import com.sayeyes.eyesapp.adapters.TheyAddedYouAdapter
import com.sayeyes.eyesapp.model.User
import com.sayeyes.eyesapp.presenters.HomePresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MainActivity : AppCompatActivity(), HomeContract.View, ItemClickListener {



    private val presenter : HomePresenter by inject { parametersOf(this) }
    var codeViewArray = arrayOf(R.id.redcode,R.id.purplecode,R.id.bluecode,R.id.yellowcode)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.prepareData(applicationContext)

        prepareCodeViews()
    }

    private fun prepareCodeViews() {
        for (i in 0 until codeViewArray.size) {
            val currentView = findViewById<EditText>(codeViewArray[i])
            currentView.clearComposingText()
            currentView.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(1))
        }

        yellowcode.afterTextChanged{
            if(bluecode.text.toString().equals("E",true) && purplecode.text.toString().equals("Y",true) && redcode.text.toString().equals("E",true) && yellowcode.text.toString().equals("S",true)){
                showToast(R.string.success)
            } else {
                showToast(R.string.failure)
            }
        }
    }

    private fun showToast(res : Int) {
        val toast = Toast.makeText(applicationContext, res, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 200)
        toast.show()
    }

    override fun displayData(theyAddedYouUsers: List<User>, suggestedUsers: List<User>) {
        they_added_you_recycler_view.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        they_added_you_recycler_view.adapter = TheyAddedYouAdapter(applicationContext,theyAddedYouUsers,this)
        LinearSnapHelper().attachToRecyclerView(they_added_you_recycler_view)
        they_added_you_recycler_view.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.HORIZONTAL))
        they_added_you_recycler_view.isNestedScrollingEnabled = false;

        suggested_users_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        suggested_users_recycler_view.adapter = SuggestionsAdapter(applicationContext,suggestedUsers,this)
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

    override fun updateSuggestedUsers(clear: Boolean) {
        if(clear){
            suggested_label.visibility = View.GONE
            suggested_users_recycler_view.adapter = null
        } else {
            suggested_users_recycler_view.adapter?.notifyDataSetChanged()
        }
    }

    override fun deleteItemAt(tag: HomeTags, position: Int) {
        presenter.removeUserFromPosition(tag, position)
    }

    override fun addItemAt(tag: HomeTags, position: Int) {
        presenter.addUserAtPosition(tag, position)
    }
}
