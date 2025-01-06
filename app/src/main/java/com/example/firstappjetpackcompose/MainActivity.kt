package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomButtonsGame()
        }
    }
}

@Composable
fun RandomButtonsGame() {
    // Определенные цвета
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    var buttonStates by rememberSaveable { mutableStateOf(List(3) { ButtonState(colors) }) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttonStates.forEachIndexed { index, state ->
            Button(
                onClick = {
                    buttonStates = buttonStates.mapIndexed { i, s ->
                        if (i == index) s else s.randomize(colors)
                    }
                },
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                border = BorderStroke(state.borderThickness.dp, state.borderColor),
                colors = ButtonDefaults.buttonColors(containerColor = state.backgroundColor)
            ) {
                Text("Кнопка ${index + 1}")
            }
        }

        if (buttonStates.distinct().size == 1) {
            Text("Победа!", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(16.dp))
        }
    }
}

data class ButtonState(
    var backgroundColor: Color,
    var borderColor: Color,
    var borderThickness: Int
) {
    constructor(colors: List<Color>) : this(
        backgroundColor = colors[Random.nextInt(colors.size)],
        borderColor = colors[Random.nextInt(colors.size)],
            borderThickness = Random.nextInt(4, 12)
    )

    fun randomize(colors: List<Color>): ButtonState {
        return ButtonState(
            backgroundColor = colors[Random.nextInt(colors.size)],
            borderColor = colors[Random.nextInt(colors.size)],
            borderThickness = Random.nextInt(4, 12)
        )
    }
}
