package com.example.firstappjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataLoaderApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DataLoaderApp() {
    val context = LocalContext.current
    var isSwitchOn by rememberSaveable { mutableStateOf(false) }
    var dataLoaded by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF280C28),
                    titleContentColor = Color.White

                ),
                title = { Text("Загрузка данных") },
                actions = {
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(Color(0xFF280C28)),
                onClick = {
                    if (isSwitchOn) {
                        Toast.makeText(context, "Данные загружены", Toast.LENGTH_SHORT).show()
                        dataLoaded = "На краю утеса, обрывающегося в бурное море, стоял старый маяк. Его белоснежные стены были покрыты трещинами, а краска местами облупилась, но он все еще гордо поднимался над волнами, как страж, охраняющий берег. Местные жители говорили, что маяк хранит в себе множество тайн, и многие из них были связаны с его последним смотрителем, старым капитаном Эдвардом."
                    } else {
                        Toast.makeText(context, "Нет доступа", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = true,
            ) {
                Text("Загрузка данных")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Switch(
                checked = isSwitchOn,
                onCheckedChange = { isSwitchOn = it }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(16.dp)
        ) {
            Text(
                text = dataLoaded,
                modifier = Modifier.align(Alignment.TopStart)
            )
        }

    }
}
