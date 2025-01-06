package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var language by rememberSaveable {
                mutableStateOf("ru")
            }

            fun toggleLanguage() {
                language = if (language == "ru") "en" else "ru"
            }

            fun getProducts(): List<String> {
                return if (language == "ru") {
                    listOf(
                        "Яблоко",
                        "Банан",
                        "Апельсин",
                        "Груша",
                        "Виноград",
                        "Киви",
                        "Манго",
                        "Ананас",
                        "Персик",
                        "Черника",
                        "Арбуз",
                        "Лимон"
                    )
                } else {
                    listOf(
                        "Apple",
                        "Banana",
                        "Orange",
                        "Pear",
                        "Grapes",
                        "Kiwi",
                        "Mango",
                        "Pineapple",
                        "Peach",
                        "Blueberry",
                        "Watermelon",
                        "Lemon"
                    )
                }
            }


            fun getTitle(): String {
                return if (language == "ru") "Список продуктов" else "List of products"
            }

            fun getSwitchText(): String {
                return if (language == "ru") "Сменить язык" else "Switch language"
            }
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = getTitle(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.DarkGray)
                        .padding(12.dp)

                )
                LazyColumn(
                    Modifier.background(Color.LightGray).height(130.dp).padding(4.dp),
                ) {
                    items(getProducts()) { product ->
                        Text(
                            text = product,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                .fillMaxWidth()
                                .background(Color.White, shape = RoundedCornerShape(20.dp))
                                .padding(2.dp)

                        )
                    }
                }
                Text(
                    text = getSwitchText(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { toggleLanguage() }
                        .padding(vertical = 16.dp)
                )
            }


        }
    }
}


