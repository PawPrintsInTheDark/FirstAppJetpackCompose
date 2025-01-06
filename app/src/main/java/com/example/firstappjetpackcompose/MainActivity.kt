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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DynamicList()
        }


    }
}

@Preview(showSystemUi = true)
@SuppressLint("DefaultLocale")
@Composable
fun DynamicList() {
    var item by rememberSaveable {mutableStateOf("") }
    val itemList = remember { mutableStateListOf("Москва", "Токио", "Берлин") }


    Column(
        modifier = Modifier
            .fillMaxWidth() ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Динамический список",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .background(Color.DarkGray)
                    .padding(12.dp)

            )
            LazyColumn(
                Modifier
                    .background(Color.LightGray)
                    .height(130.dp)
                    .padding(10.dp),
            ) {
                items(itemList) { item ->
                    Text(
                        text = item,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                            .fillMaxWidth()
                            .background(Color.White, shape = RoundedCornerShape(20.dp))
                            .padding(2.dp)
                            .clickable {
                                itemList.remove(item)
                            }

                    )
                }
            }
        }
        OutlinedTextField(
            value = item,
            placeholder = { Text(text = "Введите текст", fontSize = 16.sp)},
            textStyle = TextStyle(fontSize = 18.sp),
            onValueChange = { item = it })

        Text(
            text = "Добавить",
            fontWeight = FontWeight.W900,
            fontSize = 24.sp,
            modifier = Modifier
                .clickable {
                    if (item.isNotBlank()) {
                        itemList.add(item)
                        item = ""
                    }
                }
                .padding(12.dp)
        )
    }
}
