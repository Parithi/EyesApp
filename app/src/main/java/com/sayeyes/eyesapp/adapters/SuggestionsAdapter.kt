package com.sayeyes.eyesapp.adapters

import android.content.Context
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sayeyes.eyesapp.HomeTags
import com.sayeyes.eyesapp.ItemClickListener
import com.sayeyes.eyesapp.R
import com.sayeyes.eyesapp.model.User
import de.hdodenhof.circleimageview.CircleImageView

class SuggestionsAdapter(var context : Context, var users : List<User>,var listener : ItemClickListener) : RecyclerView.Adapter<SuggestionsAdapter.SuggestedUserViewHolder>() {

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

        if(user.added){
            setUserAdded(viewHolder)
        }

        viewHolder.call.visibility = if(user.hasPhone) View.VISIBLE else View.GONE
        viewHolder.favorite.visibility = if(user.hasFavorite) View.VISIBLE else View.GONE
        viewHolder.like.visibility = if(user.hasLikes) View.VISIBLE else View.GONE
        viewHolder.chat.visibility = if(user.hasChat) View.VISIBLE else View.GONE
        viewHolder.rssfeed.visibility = if(user.hasRssFeed) View.VISIBLE else View.GONE

        if(!user.added){
            viewHolder.add.setOnClickListener{
                viewHolder.add.text = context.getString(R.string.adding_label)
                Handler().postDelayed({
                    listener.addItemAt(HomeTags.SUGGESTED_USERS,position)
                    setUserAdded(viewHolder)
                },1500)
            }
        }


        viewHolder.delete.setOnClickListener{
            listener.deleteItemAt(HomeTags.SUGGESTED_USERS,position)
        }
    }

    private fun setUserAdded(viewHolder: SuggestedUserViewHolder) {
        viewHolder.add.text = context.getString(R.string.added_label)
        viewHolder.delete.visibility = View.GONE
        viewHolder.add.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        viewHolder.add.setTextColor(ContextCompat.getColor(context, R.color.codeBlue))
        viewHolder.add.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }


    inner class SuggestedUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileImageView = itemView.findViewById<CircleImageView>(R.id.profile_image)!!
        var profileName = itemView.findViewById<TextView>(R.id.profile_name)!!
        var school = itemView.findViewById<TextView>(R.id.school)!!
        var grade = itemView.findViewById<TextView>(R.id.grade)!!

        var call = itemView.findViewById<ImageView>(R.id.call)!!
        var favorite = itemView.findViewById<ImageView>(R.id.favorite)!!
        var like = itemView.findViewById<ImageView>(R.id.like)!!
        var chat = itemView.findViewById<ImageView>(R.id.chat)!!
        var rssfeed = itemView.findViewById<ImageView>(R.id.rssfeed)!!

        var delete = itemView.findViewById<ImageView>(R.id.delete_button)!!
        var add = itemView.findViewById<TextView>(R.id.add_button)!!
    }
}