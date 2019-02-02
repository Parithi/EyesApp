package com.sayeyes.eyesapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sayeyes.eyesapp.R
import com.sayeyes.eyesapp.model.User
import de.hdodenhof.circleimageview.CircleImageView

class SuggestionsAdapter(var context : Context, var users : List<User>) : RecyclerView.Adapter<SuggestionsAdapter.SuggestedUserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p: Int): SuggestionsAdapter.SuggestedUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.suggestions_row, parent, false)
        return SuggestedUserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(viewHolder: SuggestionsAdapter.SuggestedUserViewHolder, position: Int) {
        val user = users.get(position)
        viewHolder.profileName.text = user.name
        viewHolder.school.text = user.school
        viewHolder.grade.text = user.grade

        Glide.with(context)
            .load(user.profileImageUrl)
            .into(viewHolder.profileImageView)
    }


    inner class SuggestedUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileImageView = itemView.findViewById<CircleImageView>(R.id.profile_image)
        var profileName = itemView.findViewById<TextView>(R.id.profile_name)
        var school = itemView.findViewById<TextView>(R.id.school)
        var grade = itemView.findViewById<TextView>(R.id.grade)
    }
}