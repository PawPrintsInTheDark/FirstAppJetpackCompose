package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Note(
    val id: Int,
    val content: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesApp()
        }
    }
}

@Composable
fun NotesApp() {
    var notes by rememberSaveable { mutableStateOf(listOf<Note>()) }
    var noteContent by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue()) }

    Box(modifier = Modifier
        .padding(10.dp)
        .fillMaxSize()
        .clip(RoundedCornerShape(5.dp))) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Заметки",
                fontSize = 28.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray, RoundedCornerShape(2.dp))
                    .padding(10.dp),
                color = Color.White,
            )
            TextField(
                value = noteContent,
                onValueChange = { noteContent = it },
                label = { Text("Содержимое заметки", fontSize = 20.sp) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 18.sp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray, RoundedCornerShape(3.dp))
            ) {
                items(notes) { note ->
                    NoteItem(note) { notes = notes.filter { it.id != note.id } }
                }
            }
        }
        FloatingActionButton(
            containerColor = Color.DarkGray,
            onClick = {
                if (noteContent.text.isNotBlank()) {
                    val newNote = Note(
                        id = notes.size + 1,
                        content = noteContent.text
                    )
                    notes += newNote
                    noteContent = TextFieldValue()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
        ) {
            Text("+")
        }
    }
}

@Composable
fun NoteItem(note: Note, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = note.content, fontSize = 18.sp)
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Удалить заметку", tint = Color.DarkGray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotePreview() {
    NoteItem(Note(1, "hello")) {}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesApp()
}
