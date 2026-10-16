package com.example.maninthemirror

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.maninthemirror.data.ContactRepository
import com.example.maninthemirror.model.Contact
import com.example.maninthemirror.model.Message
import com.example.maninthemirror.ui.screens.ContactList
import com.example.maninthemirror.ui.screens.Conversation
import com.example.maninthemirror.ui.screens.sampleContacts
import com.example.maninthemirror.ui.theme.ManInTheMirrorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val repository = ContactRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ManInTheMirrorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val scope = rememberCoroutineScope()

                    var selectedContact by remember { mutableStateOf<Contact?>(null) }
                    val contactsList = remember { mutableStateListOf<Contact>() }
                    val messagesList = remember { mutableStateListOf<Message>() }

                    // Fetch contacts and messages from Supabase
                    LaunchedEffect(Unit) {
                        try {
                            val remoteContacts = repository.getContacts()
                            if (remoteContacts.isNotEmpty()) {
                                contactsList.clear()
                                contactsList.addAll(remoteContacts)
                            } else {
                                contactsList.addAll(sampleContacts)
                            }
                        } catch (_: Exception) {
                            contactsList.addAll(sampleContacts)
                        }

                        try {
                            val remoteMessages = repository.getMessages()
                            if (remoteMessages.isNotEmpty()) {
                                messagesList.clear()
                                messagesList.addAll(remoteMessages)
                            } else {
                                messagesList.addAll(SampleData.conversationSample)
                            }
                        } catch (_: Exception) {
                            messagesList.addAll(SampleData.conversationSample)
                        }
                    }
                    val currentMessages = messagesList.filter { it.contactId == selectedContact?.id }


                    if (selectedContact == null) {
                        ContactList(
                            contacts = contactsList,
                            onContactClick = { contact ->
                                selectedContact = contact
                            }
                        )
                    } else {
                        Conversation(
                            messages = currentMessages,
                            contactName = selectedContact?.name ?: "Chat",
                            onBackClick = {
                                selectedContact = null
                            },
                            onSendMessage = { text ->

                                val contactIdentity = selectedContact?.id ?: return@Conversation
                                val newMessage = Message(contactId = contactIdentity,author = "Me", body = text)
                                messagesList.add(newMessage)
                                scope.launch {
                                    try {
                                        repository.sendMessage(newMessage)
                                    } catch (_: Exception) {
                                        // Handled gracefully
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
