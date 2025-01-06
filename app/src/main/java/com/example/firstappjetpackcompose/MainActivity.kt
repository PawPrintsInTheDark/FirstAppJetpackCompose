package com.example.firstappjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BodyMassIndexCalculator()
        }


    }
}

@SuppressLint("DefaultLocale")
@Composable
fun BodyMassIndexCalculator() {
    var weight by rememberSaveable { mutableIntStateOf(0) }
    var height by rememberSaveable { mutableIntStateOf(0) }
    val bmi by remember {
        derivedStateOf {
            if (height != 0) weight / ((height / 100.0).pow(2)) else 0.0
        }
    }
    val interpretation by remember {
        derivedStateOf {
            if (height > 0) {
                when {
                    bmi < 16.0 -> "Выраженный дефицит массы тела"
                    bmi in 16.0..18.49 -> "Недостаточная масса тела"
                    bmi in 18.5..24.9 -> "Нормальная  масса тела"
                    bmi in 25.0..29.9 -> "Избыточная масса тела (предожирение)"
                    bmi in 30.0..34.9 -> "Ожирение 1-ой степени"
                    bmi in 35.0..39.9 -> "Ожирение 2-ой степени"
                    bmi < 40.0 -> "Ожирение 3-й степени"
                    else -> "Бабушка откормила"
                }
            } else {
                "Недостаточно данных!"
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Калькулятор ИМТ",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(12.dp)

        )
        Text(
            text = "Рост:",
            fontSize = 16.sp,
            modifier = Modifier
                .clickable { height += 5 }
                .padding(top = 8.dp)
        )
        Text(
            text = "$height см",
            fontSize = 16.sp,
            modifier = Modifier

                .clickable { height += 5 }
                .padding(bottom = 4.dp)
        )
        Text(
            text = "Вес:",
            fontSize = 16.sp,
            modifier = Modifier
                .clickable { weight += 5 }
                .padding(top = 8.dp)
        )
        Text(
            text = "$weight кг",
            fontSize = 16.sp,
            modifier = Modifier
                .clickable { weight += 5 }
                .padding(bottom = 12.dp)
        )
        Text(text = "Коэффицент ИМТ:", fontSize = 16.sp)
        Text(
            text = String.format("%.0f", bmi),
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Text(
            text = interpretation,
            fontSize = 16.sp,
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(5.dp)
        )
        Text(
            text = "Сбросить",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .clickable {
                    weight = 0
                    height = 0
                }
                .padding(12.dp)
        )
    }
}
