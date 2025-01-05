package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val images = listOf(
            R.drawable.apple,
            R.drawable.banana,
            R.drawable.orange,
            R.drawable.mango,
            R.drawable.cucumber,
            R.drawable.strawberry,
        )
        val randomImages = List(102) { images[Random.nextInt(images.size)] }

        setContent {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
            ) {
                items(102) { index ->
                    ProductRow(randomImages[index])
                }
            }
        }
    }
}

data class ProductModel(val name: String, val img: Int)

@Composable
fun ProductRow(img: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(3.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.White)
    )
    {
        Image(
            painter = painterResource(id = img),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .padding(start = 20.dp)
        )
    }
}