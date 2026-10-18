package com.example.maninthemirror.data

import com.example.maninthemirror.model.Contact
import com.example.maninthemirror.model.Message
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import kotlinx.coroutines.flow.Flow

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

    suspend fun subscribeToMessages(): Flow<PostgresAction> {
        val channel = SupabaseClient.client.channel("messages_channel")
        val changeFlow = channel.postgresChangeFlow<PostgresAction>(schema = "public") {
            table = "messages"
        }
        channel.subscribe()
        return changeFlow
    }
}
