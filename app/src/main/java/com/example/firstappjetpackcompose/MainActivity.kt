package com.example.firstappjetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.io.Serializable

data class Note(
    val id: Int,
    val content: String
): Serializable

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
    var openDialog by rememberSaveable { mutableStateOf(false) }
    var notes by rememberSaveable { mutableStateOf(listOf<Note>()) }
    var noteContent by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }
    var noteToDelete by rememberSaveable { mutableStateOf<Note?>(null) }
    val context = LocalContext.current

    if (openDialog) {
        DialogWithImage(
            onDismissRequest = { openDialog = false; noteToDelete = null },
            onConfirmation = {
                notes = notes.filter { it.id != noteToDelete!!.id }
                openDialog = false
                Toast.makeText(context, "Элемент удалён", Toast.LENGTH_SHORT).show()
                noteToDelete = null
            },
            painter = Icons.Default.Delete,
        )
    }

    Box(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(5.dp))
    ) {

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
                    NoteItem(note) {
                        noteToDelete = note
                        openDialog = true
                    }
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
fun DialogWithImage(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: ImageVector,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter, contentDescription = "",
                    modifier = Modifier.height(160.dp).fillMaxSize(),
                    tint = Color(0xFFD23A62)
                )
                Text(text = "Потвердите удаление элемента", Modifier.padding(16.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    Arrangement.Center
                ) {
                    TextButton(onClick = {onDismissRequest()},
                        modifier = Modifier.padding(8.dp)) {
                        Text(text = "Отмена")
                    }
                    TextButton(onClick = {onConfirmation()},
                        modifier = Modifier.padding(8.dp)) {
                        Text(text = "Удалить")
                    }
                }
            }
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
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Удалить заметку",
                    tint = Color.DarkGray
                )
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
