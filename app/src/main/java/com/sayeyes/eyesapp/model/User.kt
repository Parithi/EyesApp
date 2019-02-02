package com.sayeyes.eyesapp.model

data class User(
    var id : String,
    var name : String,
    var school : String,
    var grade : String,
    var hasPhone : Boolean,
    var hasFavorite : Boolean,
    var hasLikes : Boolean,
    var hasChat : Boolean,
    var hasRssFeed : Boolean,
    var added : Boolean,
    var profileImageUrl : String
)