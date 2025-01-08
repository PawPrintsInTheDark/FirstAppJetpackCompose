package com.example.firstappjetpackcompose

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneBookApp() { finish() }
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneBookApp(onClose: () -> Unit) {
    var textContent by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }
    val context = LocalContext.current
    var contacts by rememberSaveable { mutableStateOf(listOf<String>()) }
    var selectedContact by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Телефонная книга") },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, tint = Color.White, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        selectedContact?.let {
                            showToast(
                                "Звонок совершен: $it",
                                context
                            )
                        }
                    }) {
                        Icon(Icons.Default.Call, tint = Color.White, contentDescription = "Call")
                    }
                    IconButton(onClick = { onClose() }) {
                        Icon(Icons.Default.Close, tint = Color.White, contentDescription = "Close")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {
                        selectedContact?.let {
                            showToast(
                                "Сообщение отправлено: $it",
                                context
                            )
                        }
                    }) {
                        Icon(Icons.AutoMirrored.Default.Send, contentDescription = "Send")
                    }
                    Spacer(Modifier.weight(1f, true))
                    IconButton(onClick = {
                        selectedContact?.let {
                            showToast(
                                "Контакт отредактирован: $it",
                                context
                            )
                        }
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,

                )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (textContent.text.isNotBlank()) {
                    contacts += textContent.text
                    textContent = TextFieldValue()
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            OutlinedTextField(
                value = textContent,
                onValueChange = { textContent = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                Modifier
                    .background(MaterialTheme.colorScheme.inverseOnSurface, RoundedCornerShape(10.dp))
                    .fillMaxSize()) {
                items(contacts) { contact ->
                    ContactItem(contact, onClick = {
                        selectedContact = contact
                    }, onDelete = {
                        contacts = contacts.filter { it != contact }
                        })
                }
            }
        }
    }
}

@Composable
fun ContactItem(contact: String, onClick: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
            .background(Color.White, CircleShape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(contact, modifier = Modifier.weight(1f).padding(start = 4.dp), fontWeight = FontWeight.W400, fontSize = 18.sp)
        IconButton(onClick = onDelete) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}

fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhoneBookApp() { }
}

@Preview(showBackground = true)
@Composable
fun ContactPreview() {
    ContactItem("hello guys!",{}) { }
}
