package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val persons = remember {
                listOf(
                    Person("Иван", 50000, generatePhone()),
                    Person("Петр", 60000, generatePhone()),
                    Person("Анна", 55000, generatePhone()),
                    Person("Дмитрий", 55000, generatePhone()),
                    Person("Яна", 55000, generatePhone()),
                    Person("Гриша", 55000, generatePhone()),
                    Person("Игорь", 55000, generatePhone()),
                    Person("Алексей", 55000, generatePhone()),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = "Данные по персоналу",
                    fontSize = 28.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Black)
                        .background(Color.DarkGray)
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.DarkGray)
                        .background(Color.LightGray)
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Имя", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Зарплата", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Телефон", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                for (item in persons) {
                    PersonItem(person = item)
                }

            }

        }
    }

    private fun generatePhone(): String {
        val random = Random
        val number = StringBuilder("+7917")
        for (i in 0 until 7) {
            number.append(random.nextInt(0, 10))
        }
        return number.toString()
    }
}

@Composable
fun PersonItem(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.DarkGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = person.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = person.salary.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = person.phone, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}


