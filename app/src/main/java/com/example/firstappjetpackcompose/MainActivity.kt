package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val employees = listOf(
            Employee("Иван", "Иванов", "Инженер", 50000.0),
            Employee("Петр", "Петров", "Врач", 70000.0),
            Employee("Сергей", "Сергеев", "Программист", 60000.0),
            Employee("Алексей", "Алексеев", "Учитель", 40000.0),
            Employee("Анна", "Антонова", "Инженер", 52000.0),
            Employee("Мария", "Маркова", "Врач", 72000.0),
            Employee("Дмитрий", "Дмитриев", "Программист", 65000.0),
            Employee("Елена", "Еленина", "Учитель", 45000.0),
            Employee("Олег", "Олегов", "Инженер", 55000.0),
            Employee("Татьяна", "Татьянова", "Врач", 75000.0),
            Employee("Андрей", "Андреев", "Программист", 68000.0),
            Employee("Светлана", "Светланова", "Учитель", 48000.0)
        )
        val sortedEmployees = employees.shuffled()

        setContent {
            FlowColumn(
                Modifier
                    .fillMaxSize()
                    .horizontalScroll(rememberScrollState()),
            ) {
                sortedEmployees.forEach { employee ->
                    Box(
                        Modifier
                            .wrapContentSize()
                            .padding(6.dp)
                            .border(2.dp, Color.DarkGray)
                            .background(Color.Gray)
                    ) {
                        EmployeeCard(employee = employee)
                    }
                }
            }
        }
    }
}

data class Employee(
    val firstName: String, val lastName: String, val position: String, val salary: Double
)


@Composable
fun EmployeeCard(employee: Employee) {
    Column(
        modifier = Modifier
            .width(400.dp)
    ) {
        Image(painterResource(id = R.drawable.ic_android), "",
            modifier = Modifier.padding(10.dp).size(100.dp))
        Text(text = "Имя: ${employee.firstName}", fontSize = 22.sp )
        Text(text = "Фамилия: ${employee.lastName}", fontSize = 22.sp)
        Text(text = "Должность: ${employee.position}", fontSize = 22.sp)
        Text(text = "Зарплата: ${employee.salary} руб.", fontSize = 22.sp)
    }
}