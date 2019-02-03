package com.sayeyes.eyesapp

interface ItemClickListener {
    fun deleteItemAt(tag : HomeTags, position : Int)
    fun addItemAt(tag: HomeTags, position: Int)
}