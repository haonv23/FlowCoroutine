package com.example.flowcoroutine

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.flowcoroutine.ui.theme.BlackBackground
import com.example.flowcoroutine.ui.theme.FlowCoroutineTheme
import com.example.flowcoroutine.ui.theme.PurpleTextTitle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlowCoroutineTheme {

            }
        }
    }
}

var cachedDensityScale: Float = 1f

fun Number.pxToDp(): Dp {
    val scale = cachedDensityScale
    return (this.toFloat() * scale).dp
}

@Composable
fun Dp.toSp(): TextUnit {
    val density = LocalDensity.current
    return with(density) { this@toSp.toSp() }
}

@Composable
fun Popup(
    titleError: String,
    descriptionError: String,
    firstActionText: String,
    secondActionText: String
) {
    Column(
        modifier = Modifier
            .width(340.pxToDp())
            .heightIn(
                min = 296.pxToDp(),
                max = 324.pxToDp()
            )
            .clip(RoundedCornerShape(24.pxToDp()))
            .background(BlackBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.pxToDp()))
        Text(
            text = titleError,
            modifier = Modifier
                .widthIn(max = 276.pxToDp()),
            color = PurpleTextTitle,
            fontSize = 22.pxToDp().toSp(),
            lineHeight = 28.pxToDp().toSp(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.pxToDp()))
        Text(
            text = descriptionError,
            modifier = Modifier
                .widthIn(max = 276.pxToDp()),
            color = Color.White,
            fontSize = 16.pxToDp().toSp(),
            lineHeight = 24.pxToDp().toSp(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.pxToDp()))
        Box(
            modifier = Modifier
                .width(276.pxToDp())
                .height(44.pxToDp())
                .clip(RoundedCornerShape(999.pxToDp()))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFFF6D79D), Color(0xFFD4A249)),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1000f)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = firstActionText,
                modifier = Modifier,
                fontSize = 18.pxToDp().toSp(),
                lineHeight = 26.pxToDp().toSp(),
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.height(12.pxToDp()))
        Box(
            modifier = Modifier
                .width(276.pxToDp())
                .height(44.pxToDp())
                .clip(RoundedCornerShape(999.pxToDp()))
                .background(Color(0xFF4A0A82)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = secondActionText,
                modifier = Modifier,
                fontSize = 18.pxToDp().toSp(),
                lineHeight = 26.pxToDp().toSp(),
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.height(32.pxToDp()))
    }
}

@Composable
fun DropDown(list: List<String>) {
    var selectedGender by remember { mutableStateOf(list[0]) }
    Column(
        modifier = Modifier
            .width(340.pxToDp())
            .heightIn(
                min = 120.pxToDp(),
                max = 176.pxToDp()
            )
            .clip(RoundedCornerShape(24.pxToDp()))
            .background(BlackBackground)
            .padding(20.pxToDp())
    ) {
        list.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.pxToDp()),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    modifier = Modifier
                        .size(20.pxToDp()),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF9610FF),
                        unselectedColor = Color(0xFF9610FF)
                    ),
                    selected = selectedGender == item,
                    onClick = {
                        selectedGender = item
                    }
                )
                Spacer(modifier = Modifier.width(12.pxToDp()))
                Text(
                    text = item,
                    modifier = Modifier,
                    fontSize = 16.pxToDp().toSp(),
                    color = Color.White,
                )
            }
            if (item != list.last()) {
                Spacer(
                    modifier = Modifier
                        .height(16.pxToDp())
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.pxToDp())
                        .background(Color(0xFF35383F))
                )
                Spacer(
                    modifier = Modifier
                        .height(16.pxToDp())
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DropDownPreview() {
    DropDown(listOf("Left Hand", "Right hand"))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlowCoroutineTheme {
        Popup(
            "Hands Unrecognized",
            "We were unable to recognize the hands in the image. Please try again with a different image.",
            "Choose Another Image",
            "Cancel"
        )
    }
}