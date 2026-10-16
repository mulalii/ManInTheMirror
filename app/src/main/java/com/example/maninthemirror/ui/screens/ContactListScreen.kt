package com.example.maninthemirror.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.maninthemirror.model.Contact
import com.example.maninthemirror.ui.components.ContactItem
import com.example.maninthemirror.ui.theme.ManInTheMirrorTheme

val sampleContacts = listOf(
    Contact(1, "James"),
    Contact(2, "John"),
    Contact(3, "Peter"),
    Contact(4, "Matthew"),
    Contact(5, "Mark"),
    Contact(6, "Luke"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactList(
    modifier: Modifier = Modifier,
    contacts: List<Contact> = sampleContacts,
    onContactClick: (Contact) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Contacts") }
            )
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(contacts) { contact ->
                ContactItem(contact = contact, onClick = onContactClick)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContactList() {
    ManInTheMirrorTheme {
        ContactList()
    }
}
