package com.example.firstappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        enableEdgeToEdge()
        val productList = mutableListOf(
            ProductModel("Apple", R.drawable.apple),
            ProductModel("Orange", R.drawable.orange),
            ProductModel("Strawberry", R.drawable.strawberry),
            ProductModel("Banana", R.drawable.banana),
            ProductModel("Mango", R.drawable.mango),
            ProductModel("Apple", R.drawable.apple),
            ProductModel("Orange", R.drawable.orange),
            ProductModel("Strawberry", R.drawable.strawberry),
            ProductModel("Banana", R.drawable.banana),
            ProductModel("Mango", R.drawable.mango),
            ProductModel("Apple", R.drawable.apple),
            ProductModel("Orange", R.drawable.orange),
            ProductModel("Strawberry", R.drawable.strawberry),
            ProductModel("Banana", R.drawable.banana),
            ProductModel("Mango", R.drawable.mango),
            ProductModel("Apple", R.drawable.apple),
            ProductModel("Orange", R.drawable.orange),
            ProductModel("Strawberry", R.drawable.strawberry),
            ProductModel("Banana", R.drawable.banana),
            ProductModel("Mango", R.drawable.mango),
        )
        setContent {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Фрукты", modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .border(2.dp, Color.Gray)
                        .padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                        .border(2.dp, Color.Gray)
                        .padding(5.dp),
                ) {
                    items(productList) { product ->
                        ProductRow(model = product)
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
                Text(
                    text = "Другие фрукты", modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                        .border(2.dp, Color.Gray)
                        .padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                        .border(2.dp, Color.Gray)
                        .padding(5.dp),
                ) {
                    items(productList) { product ->
                        ProductRow(model = product)
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}

data class ProductModel(val name: String, val img: Int)


@Composable
fun ProductRow(model: ProductModel) {
    var isClick by remember {
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .clickable { isClick = !isClick }
            .background(if (isClick) Color.Gray else Color.LightGray)
            .padding(end = 20.dp),
    )
    {
        Image(
            painter = painterResource(id = model.img),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp)
                .padding(5.dp)
        )
        Text(
            text = model.name,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}