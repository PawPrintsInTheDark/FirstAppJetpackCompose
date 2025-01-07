package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            CarConfiguratorApp()
        }
    }
}

@Composable
fun CarConfiguratorApp() {

    var selectedColor by remember { mutableStateOf("Красный") }
    var selectedPackage by remember { mutableStateOf("Classic") }
    val prices = mapOf(
        "Classic" to 800000,
        "Comfort" to 950000,
        "Luxe" to 1200000,
        "Style" to 1400000
    )
    val colors = listOf(
        "Серебряный" to Color(166, 168, 180, 255),
        "Красный" to Color(122, 75, 69, 255),
        "Чёрный" to Color(24, 25, 27, 255),
        "Синий" to Color(3, 38, 98, 255),
        "Серый" to Color(79, 79, 86, 255),
    )
    val packages = listOf("Classic", "Comfort", "Luxe", "Style")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = getCarImage(selectedColor)),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
                    .padding(end = 30.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.BottomCenter
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        ColorSelector(colors, selectedColor) { selectedColor = it }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, shape = RoundedCornerShape(16.dp)),
        ) {
            packages.forEach { packageName ->
                PackageRadioButton(packageName, selectedPackage) { selectedPackage = it }
            }

            Text(
                text = "Cтоимость: ${prices[selectedPackage]} руб.",
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray, RoundedCornerShape(20.dp))
                    .padding(5.dp)
            )
        }

    }


}

@Composable
fun ColorSelector(
    colors: List<Pair<String, Color>>,
    selectedColor: String,
    onColorSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            colors.forEach { (colorName, color) ->
                ColorCircle(colorName, selectedColor, color, onColorSelected)
            }
        }
    }
}


@Composable
fun ColorCircle(
    colorName: String,
    selectedColor: String,
    color: Color,
    onColorSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(color)
            .border(
                width = if (colorName == selectedColor) 3.dp else 1.dp,
                color = if (colorName == selectedColor) Color.Black else Color.Gray,
                shape = CircleShape
            )
            .selectable(selected = (colorName == selectedColor)) { onColorSelected(colorName) },
    )
}

@Composable
fun PackageRadioButton(
    packageName: String,
    selectedPackage: String,
    onPackageSelected: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = (packageName == selectedPackage),
            onClick = { onPackageSelected(packageName) }
        )
        Text(text = packageName)
    }
}


fun getCarImage(color: String): Int {
    return when (color) {
        "Красный" -> R.drawable.copper_car
        "Синий" -> R.drawable.blue_car
        "Чёрный" -> R.drawable.black_car
        "Серый" -> R.drawable.gray_car
        "Серебряный" -> R.drawable.silver_car
        else -> R.drawable.silver_car
    }
}