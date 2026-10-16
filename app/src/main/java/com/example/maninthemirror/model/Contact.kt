package com.example.maninthemirror.model

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id: Int,
    val name: String
)
