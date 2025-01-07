package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizApp()
        }
    }
}

@Composable
fun QuizApp() {
    var currentScreen by rememberSaveable { mutableStateOf("menu") }
    var correctAnswers by rememberSaveable { mutableIntStateOf(0) }

    when (currentScreen) {
        "menu" -> MainMenu {
            correctAnswers = 0
            currentScreen = "question1"
        }
        "question1" -> QuestionScreen1 { isCorrect ->
            if (isCorrect) correctAnswers++
            currentScreen = "question2"
        }
        "question2" -> QuestionScreen2 { isCorrect ->
            if (isCorrect) correctAnswers++
            currentScreen = "question3"
        }
        "question3" -> QuestionScreen3 { isCorrect ->
            if (isCorrect) correctAnswers++
            currentScreen = "results"
        }
        "results" -> ResultsScreen(correctAnswers) { currentScreen = "menu" }
    }
}

@Composable
fun MainMenu(onStartClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Исторический тест", fontSize = 28.sp, color = Color.White, modifier = Modifier
            .background(Color.DarkGray, shape = RoundedCornerShape(20.dp))
            .padding(8.dp))
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = onStartClick, colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)) {
            Text(text = "Начать!", color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun QuestionScreen1(onNext: (Boolean) -> Unit) {
    var selectedOptions by rememberSaveable { mutableStateOf(setOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Выберите из списка всех президентов России", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color.White, modifier = Modifier
            .background(Color.DarkGray, shape = RoundedCornerShape(20.dp))
            .padding(8.dp))

        val correctAnswers = setOf("Дмитрий Медведев", "Владимир Путин")
        val options = listOf("Дмитрий Медведев", "Леонид Брежнев", "Владимир Путин", "Михаил Горбачев")

        options.forEach { option ->
            CheckboxWithLabel(option, selectedOptions.contains(option)) {
                selectedOptions = if (selectedOptions.contains(option)) {
                    selectedOptions - option
                } else {
                    selectedOptions + option
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val isCorrect = selectedOptions == correctAnswers
            onNext(isCorrect)
        }, modifier = Modifier.align(Alignment.CenterHorizontally), colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)) {
            Text(text = "Ответить", color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun QuestionScreen2(onNext: (Boolean) -> Unit) {
    var selectedOptions by rememberSaveable { mutableStateOf(setOf<String>()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Какие из этих Русско-турецких войн были при Екатерине Второй?", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 28.sp, color = Color.White, modifier = Modifier
            .padding(12.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(15.dp))
            .padding(8.dp))

        val correctAnswers = setOf("Русско-Турецкая война 1768-1774", "Русско-турецкая война 1787-1791")
        val options = listOf("Русско-Турецкая война 1768-1774", "Русско-Турецкая война 1735-1739", "Русско-Турецкая война 1806-1812", "Русско-турецкая война 1787-1791")

        options.forEach { option ->
            CheckboxWithLabel(option, selectedOptions.contains(option), Modifier.padding(end = 15.dp)) {
                selectedOptions = if (selectedOptions.contains(option)) {
                    selectedOptions - option
                } else {
                    selectedOptions + option
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val isCorrect = selectedOptions == correctAnswers
            onNext(isCorrect)
        }, modifier = Modifier.align(Alignment.CenterHorizontally), colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)) {
            Text(text = "Ответить", color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun QuestionScreen3(onNext: (Boolean) -> Unit) {
    var selectedOption by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Какое из этих изображений является изображением шапки Мономаха?", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 28.sp, color = Color.White, modifier = Modifier
            .padding(12.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(15.dp))
            .padding(8.dp))

        val correctAnswer = R.drawable.hat1
        val options = listOf(R.drawable.hat1, R.drawable.hat2, R.drawable.hat3)

        Row(modifier = Modifier.padding(start = 20.dp)) {
            options.forEach { option ->
                SelectableImage(option, selectedOption == option) {
                    selectedOption = option
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val isCorrect = selectedOption == correctAnswer
            onNext(isCorrect)
        }, modifier = Modifier.align(Alignment.CenterHorizontally), colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)) {
            Text(text = "Ответить", color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CheckboxWithLabel(label: String, isChecked: Boolean, modifier: Modifier = Modifier, onCheckedChange: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { onCheckedChange() },
            colors = CheckboxDefaults.colors(checkedColor = Color.DarkGray)
        )
        Text(
            text = label,
            fontSize = 20.sp,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun SelectableImage(id: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        val image = painterResource(id = id)
        Image(painter = image, contentScale = ContentScale.FillBounds, contentDescription = "", modifier = Modifier.size(100.dp))

        if (isSelected) {
            Text(text = "Выбрано", color = Color.Magenta, fontSize = 20.sp)
        }
    }
}
@Composable
fun ResultsScreen(correctAnswers: Int, onRestart: () -> Unit) {
    val totalQuestions = 3

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ваш результат: $correctAnswers из $totalQuestions",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .background(Color.DarkGray, shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRestart,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Text(text = "Вернуться в главное меню", color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}