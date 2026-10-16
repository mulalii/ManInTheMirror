package com.example.maninthemirror.data

import com.example.maninthemirror.model.Contact
import com.example.maninthemirror.model.Message
import io.github.jan.supabase.postgrest.from

class ContactRepository {

    suspend fun getContacts(): List<Contact> {
        return SupabaseClient.client
            .from("contacts")
            .select()
            .decodeList<Contact>()
    }

    suspend fun getMessages(): List<Message> {
        return SupabaseClient.client
            .from("messages")
            .select()
            .decodeList<Message>()
    }

    suspend fun sendMessage(message: Message) {
        SupabaseClient.client
            .from("messages")
            .insert(message)
    }
}
