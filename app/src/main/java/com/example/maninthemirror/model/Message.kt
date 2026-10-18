package com.example.maninthemirror.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: Int? = null,
    val contactId: Int,
    val author: String,
    val body: String
){
    constructor(author: String, body: String) : this(0,0, author, body)
}