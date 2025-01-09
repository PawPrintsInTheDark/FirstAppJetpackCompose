package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppConstructorMan()
        }
    }
}

@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppConstructorMan() {
    val hairStyles = listOf("Причёска 1", "Причёска 2", "Причёска 3","Причёска 4")
    val eyebrows = listOf("Брови 1", "Брови 2")
    val noses = listOf("нос 1", "нос 2")

    var selectedHairstyle by rememberSaveable { mutableStateOf(hairStyles[0]) }
    var selectedNose by rememberSaveable { mutableStateOf(noses[0]) }
    var selectedEyebrow by rememberSaveable { mutableStateOf(eyebrows[0]) }
    var expandedMenu by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color.DarkGray
                ),
                title = {
                    Text(
                        "Конструктор персонажа",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(10.dp)
                    )
                },
                actions = {
                    IconButton(onClick = { expandedMenu = !expandedMenu }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                    DropdownMenu(
                        expanded = expandedMenu,
                        onDismissRequest = { expandedMenu = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            resetSelections(hairStyles, noses, eyebrows,
                                { selectedHairstyle = it },
                                { selectedNose = it },
                                { selectedEyebrow = it })
                        }, text = { Text("Сброс") })
                        HorizontalDivider()
                        DropdownMenuItem(onClick = {
                            randomizeSelections(hairStyles, noses, eyebrows,
                                { selectedHairstyle = it },
                                { selectedNose = it },
                                { selectedEyebrow = it })
                        }, text = { Text("Случайный образ") })
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(300.dp)) {
                Image(
                    painter = painterResource(id = getHairImageResource(selectedHairstyle)),
                    contentDescription = "Hairstyle",
                    modifier = Modifier.fillMaxSize()
                )
                Image(
                    painter = painterResource(id = getEyebrowImageResource(selectedEyebrow)),
                    contentDescription = "Eyebrows",
                    modifier = Modifier.fillMaxSize()
                )
                Image(
                    painter = painterResource(R.drawable.eye1),
                    contentDescription = "Eyes",
                    modifier = Modifier.fillMaxSize()
                )
                Image(
                    painter = painterResource(id = getNoseImageResource(selectedNose)),
                    contentDescription = "Nose",
                    modifier = Modifier.fillMaxSize()
                )
                Image(
                    painter = painterResource(R.drawable.lips1),
                    contentDescription = "Lips",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text("Причёска:", fontSize = 26.sp, fontWeight = FontWeight.Bold)
            DropdownMenuWithLabel(selectedHairstyle, hairStyles) {
                selectedHairstyle = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("Нос:", fontSize = 26.sp, fontWeight = FontWeight.Bold)
            DropdownMenuWithLabel(selectedNose, noses) {
                selectedNose = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("Брови:", fontSize = 26.sp, fontWeight = FontWeight.Bold)
            DropdownMenuWithLabel( selectedEyebrow, eyebrows) {
                selectedEyebrow = it
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuWithLabel(
    selectedValue: String,
    options: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

fun resetSelections(
    hairstyles: List<String>,
    noses: List<String>,
    eyebrows: List<String>,
    selectedHairstyle: (String) -> Unit,
    selectedNose: (String) -> Unit,
    selectedEyebrow: (String) -> Unit,
) {
    selectedHairstyle(hairstyles[0])
    selectedNose(noses[0])
    selectedEyebrow(eyebrows[0])
}

fun randomizeSelections(
    hairstyles: List<String>,
    noses: List<String>,
    eyebrows: List<String>,
    selectedHairstyle: (String) -> Unit,
    selectedNose: (String) -> Unit,
    selectedEyebrow: (String) -> Unit,
) {
    selectedHairstyle(hairstyles.random())
    selectedNose(noses.random())
    selectedEyebrow(eyebrows.random())
}


fun getHairImageResource(hairstyle: String): Int {
    return when (hairstyle) {
        "Причёска 1" -> R.drawable.hairstyle1
        "Причёска 2" -> R.drawable.hairstyle2
        "Причёска 3" -> R.drawable.hairstyle3
        "Причёска 4" -> R.drawable.hairstyle4

        else -> R.drawable.hairstyle1
    }
}

fun getEyebrowImageResource(eyebrow: String): Int {
    return when (eyebrow) {
        "Брови 1" -> R.drawable.brows1
        "Брови 2" -> R.drawable.brows2
        else -> R.drawable.brows1
    }
}


fun getNoseImageResource(nose: String): Int {
    return when (nose) {
        "нос 1" -> R.drawable.nose1
        "нос 2" -> R.drawable.nose2
        else -> R.drawable.nose1
    }
}
