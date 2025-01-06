package com.example.firstappjetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextWithLinks()
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun TextWithLinks() {
        val context = LocalContext.current
        val linkStyle = SpanStyle(Color.Magenta, textDecoration = TextDecoration.Underline)
        val annotationString = buildAnnotatedString {
            withLink(link = LinkAnnotation.Clickable(tag = "", linkInteractionListener = {
                context.startActivity(
                    Intent(
                        context,
                        SecondActivity::class.java
                    )
                )
            }, styles = TextLinkStyles(linkStyle))) {
                append("Urban")
            }
            append(" University")
        }
        Text(text = annotationString, fontSize = 28.sp, modifier = Modifier.padding(20.dp))
    }

}
