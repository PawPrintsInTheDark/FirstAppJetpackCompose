package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //1
            val emptyModifier = Modifier
            val modifier = Modifier
                .border(2.dp, Color.Red)
                .background(Color.Yellow)
                .padding(5.dp)
            MessageOne(text = "Привет", emptyModifier)
//            MessageTwo(text = "Привет")
//            MessageTwo(text = "Привет", modifier)
            //2
            val roundModifier = Modifier
                .background(Color.Cyan, CircleShape)
                .border(2.dp,Color.Green, CircleShape)
                .size(400.dp)
                .padding(top = 180.dp)
//            RoundMessage("Привет")
//            RoundMessage("Привет",roundModifier)

        }
    }
}

@Composable
fun MessageOne(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier.padding(top = 10.dp),
        fontSize = 28.sp,
    )
}


@Composable
fun MessageTwo(text: String, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        val defaultModifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 80.dp)
            .border(2.dp, Color.Red)
            .padding(vertical = 10.dp, horizontal = 35.dp)
        Text(
            text = text,
            defaultModifier.then(modifier),
            fontSize = 28.sp,
        )
    }
}

@Composable
fun RoundMessage(text: String, modifier: Modifier = Modifier) {
    val defaultModifier = Modifier
        .fillMaxWidth()
        .padding(top = 30.dp)
    Text(
        text = text,
        defaultModifier.then(modifier),
        fontSize = 28.sp,
        textAlign = TextAlign.Center,
    )
}

