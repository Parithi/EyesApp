package com.sayeyes.eyesapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sayeyes.eyesapp.HomeTags
import com.sayeyes.eyesapp.ItemClickListener
import com.sayeyes.eyesapp.R
import com.sayeyes.eyesapp.model.User
import de.hdodenhof.circleimageview.CircleImageView

class TheyAddedYouAdapter(var context : Context, var users : List<User>, var listener : ItemClickListener) : RecyclerView.Adapter<TheyAddedYouAdapter.TheyAddedYouViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p: Int): TheyAddedYouViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.they_added_you_row, parent, false)
        return TheyAddedYouViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(viewHolder: TheyAddedYouViewHolder, position: Int) {
        val user = users.get(position)
        val indexValue = position+1
        viewHolder.profileName.text = user.name
        Glide.with(context)
            .load(user.profileImageUrl)
            .into(viewHolder.profileImageView)
        viewHolder.index.text = "${indexValue}/${users.size}"

        if(user.added){
            viewHolder.nice.visibility = View.VISIBLE
            viewHolder.followImage.visibility = View.GONE
            viewHolder.follow_label.text = context.getString(R.string.followed_back_label)
            viewHolder.ignore.visibility = View.GONE
        } else {
            viewHolder.nice.visibility = View.GONE
            viewHolder.followImage.visibility = View.VISIBLE
            viewHolder.follow_label.text = context.getString(R.string.follow_back_button)
            viewHolder.ignore.visibility = View.VISIBLE
        }

        viewHolder.follow.setOnClickListener{
            listener.deleteItemAt(HomeTags.THEY_ADDED_YOU,position)
        }

        viewHolder.ignore.setOnClickListener{
            listener.deleteItemAt(HomeTags.THEY_ADDED_YOU,position)
        }
    }


    inner class TheyAddedYouViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileImageView = itemView.findViewById<CircleImageView>(R.id.profile_image)!!
        var profileName = itemView.findViewById<TextView>(R.id.profile_name)!!
        var index = itemView.findViewById<TextView>(R.id.index)!!
        var follow = itemView.findViewById<LinearLayout>(R.id.follow_button)!!
        var ignore = itemView.findViewById<LinearLayout>(R.id.ignore_button)!!
        var followImage = itemView.findViewById<ImageView>(R.id.follow_image)!!
        var nice = itemView.findViewById<TextView>(R.id.nice_button)!!
        var follow_label = itemView.findViewById<TextView>(R.id.follow_label)!!
    }
}