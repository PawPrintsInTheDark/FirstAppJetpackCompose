package com.example.firstappjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.io.Serializable

data class Note(
    val title: String,
    val content: String
):Serializable

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var notes by rememberSaveable { mutableStateOf(listOf(Note("Добро пожаловать!", "Напишите свою первую заметку!"))) }

            var showAddNoteScreen by rememberSaveable { mutableStateOf(false) }
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            if (showAddNoteScreen) {
                var title by rememberSaveable { mutableStateOf("") }
                var content by rememberSaveable { mutableStateOf("") }

                Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                    ) {
                        Text(text = "Напишите что-нибудь:", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Заголовок") }
                        )
                        OutlinedTextField(
                            value = content,
                            onValueChange = { content = it },
                            label = { Text("Основное содержимое") }
                        )
                        Button(onClick = {
                            if (title.isEmpty() || content.isEmpty()) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Введите текст!",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            } else {
                                notes += Note(title, content)
                                showAddNoteScreen = false
                            }
                        }) {
                            Text("Сохранить")
                        }
                    }
                }
            } else {
                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { showAddNoteScreen = true }) {
                            Icon(Icons.Default.Add, contentDescription = "Add Note")
                        }
                    }
                ) {
                    var selectedNote by rememberSaveable { mutableStateOf(notes[0]) }
                    val drawerState = rememberDrawerState(DrawerValue.Closed)

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                notes.forEach { note ->
                                    NavigationDrawerItem(
                                        label = { Text(note.title, fontSize = 20.sp) },
                                        selected = selectedNote == note,
                                        icon = {
                                            IconButton(onClick = {
                                                if (notes.size > 1) {
                                                    selectedNote = if (selectedNote == note) notes[notes.indexOf(selectedNote) - 1] else selectedNote
                                                    notes = notes.filter { it != note }
                                                } else {
                                                    scope.launch { snackbarHostState.showSnackbar("Добавьте хотя бы одну заметку") }
                                                }
                                            }) {
                                                Icon(
                                                    Icons.Default.Delete,
                                                    contentDescription = "Удалить заметку"
                                                )
                                            }
                                        },
                                        onClick = { selectedNote = note },
                                    )
                                }
                            }
                        }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .fillMaxWidth()
                        ) {
                            IconButton(
                                modifier = Modifier.align(Alignment.Start),
                                onClick = { scope.launch { drawerState.open() } },
                                content = { Icon(Icons.Filled.Menu, contentDescription = "Меню") }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(selectedNote.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(selectedNote.content, fontSize = 22.sp)
                        }
                    }
                }
            }
        }
    }
}
