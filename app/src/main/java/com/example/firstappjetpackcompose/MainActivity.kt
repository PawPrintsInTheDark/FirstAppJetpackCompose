package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val employees = listOf(
            Employee("Иван", "Инженер"), Employee("Петр", "Врач"),
            Employee("Сергей", "Программист"), Employee("Алексей", "Учитель"),
            Employee("Анна", "Инженер"), Employee("Мария", "Врач"),
            Employee("Дмитрий", "Программист"), Employee("Елена", "Учитель"),
            Employee("Олег", "Инженер"), Employee("Татьяна", "Врач"),
            Employee("Андрей", "Программист"), Employee("Светлана", "Учитель"),
            Employee("Максим", "Инженер"), Employee("Ксения", "Врач"),
            Employee("Анастасия", "Программист"), Employee("Игорь", "Учитель"),
            Employee("Виктор", "Инженер"), Employee("Наталья", "Врач"),
            Employee("Роман", "Программист"), Employee("Екатерина", "Учитель"),
            Employee("Юлия", "Инженер"), Employee("Станислав", "Врач"),
            Employee("Александр", "Программист"), Employee("Ольга", "Учитель"),
            Employee("Денис", "Инженер"), Employee("Марина", "Врач"),
            Employee("Владимир", "Программист"), Employee("Тимур", "Учитель"),
            Employee("Людмила", "Инженер")
        )


        val sortedEmployees = employees.shuffled()
        val groups = sortedEmployees.groupBy { it.position }

        setContent {
            val listState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()

            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(6.dp)
            ) {
                item {
                    Text(
                        text = "В конец", Modifier
                            .padding(8.dp)
                            .background(Color.DarkGray)
                            .padding(6.dp)
                            .clickable {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(employees.size-1)
                                }
                            }, fontSize = 28.sp, color = Color.White
                    )
                }
                groups.forEach { (type, name) ->
                    stickyHeader {
                        Text(
                            text = type,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .background(Color.Magenta)
                                .padding(6.dp)
                                .fillParentMaxWidth()
                        )
                    }
                    items(name) { emploee ->
                        Text(text = emploee.firstName, Modifier.padding(6.dp), fontSize = 32.sp)
                    }
                }
                item {
                    Text(
                        text = "В начало", Modifier
                            .padding(8.dp)
                            .background(Color.DarkGray)
                            .padding(6.dp)
                            .clickable {
                                coroutineScope.launch {
                                    listState.animateScrollToItem(0)
                                }
                            }, fontSize = 28.sp, color = Color.White
                    )
                }
            }
        }
    }
}

data class Employee(
    val firstName: String, val position: String
)
