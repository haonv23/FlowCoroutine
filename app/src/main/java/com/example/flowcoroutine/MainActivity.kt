package com.example.flowcoroutine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flowcoroutine.ui.theme.BlackBackground
import com.example.flowcoroutine.ui.theme.FlowCoroutineTheme
import com.example.flowcoroutine.ui.theme.PurpleTextTitle

/*
Mỗi ứng dụng hoạt động được gắn với các task, trong một task hoat động theo stack chứa các activity, fragment nếu được addToBackStack
Các launch mode:
Standard
- Luôn tạo một instance mới của Activity trong stack của task, dù đã có instance trước đó hay chưa.
    1. A → B → C
    2. A → B → C → D
- Nếu gọi lại B (Standard):
    3. A → B → C → D → B (thêm instance mới của B)
SingleTop
- Nếu instance của Activity đã ở đầu stack, hệ thống không tạo mới, mà gọi onNewIntent() của Activity đó.
    1. A → B
    2. A → B (onNewIntent())
- Nếu không có C ở đầu stack, sẽ tạo mới.
    3. A → B → C
- Tương tự Flag singletop
SingleTask
- Nếu Activity đã tồn tại trong stack (ở bất kỳ đâu):
    → Hệ thống gọi onNewIntent() và xóa hết các Activity phía trên nó.
- Nếu chưa có → tạo mới.
    Chỉ có 1 instance duy nhất của Activity này tồn tại trong 1 task.
    1. A → B → C → D, Gọi E (SingleTask):
    2. A → B → C → D → E, Gọi B (SingleTask):
    -  A → B (xóa các activity trên B)
- Tương tự Flag singletop + cleartask
SingleInstance
- Hệ thống tạo task mới riêng biệt để chứa Activity này.
- Không có Activity khác được tạo thêm vào task này.
    + Task 1: A → B → C → D
- Gọi E (SingleInstance):
    + Task 2: E riêng biệt
- Quay lại Task 1 vẫn giữ nguyên

Activity Flag:
- Default: Luôn tạo một instance mới
- FLAG_ACTIVITY_SINGLE_TOP:
    + Nếu activity được gọi đã tồn tại trên đỉnh stack, không tạo activity mới,
        lọt vào callback onNewIntent()
    + Nếu activity được gọi chưa tồn tại hoặc tồn tại mà không trên đỉnh thì vẫn tạo instance mới của activity đó
- FLAG_ACTIVITY_CLEAR_TOP:
    + Nếu activity được gọi đã tồn tại, loại bỏ tất cả activity nằm trên đó và kể cả activity được gọi,
        tạo một instance mới của activity được gọi
      1. A → B → C → A, gọi B với CLEAR_TOP
      2. A → B (instance mới)
    + Nếu sử dụng với cờ FLAG_ACTIVITY_SINGLE_TOP, onNewIntent() được gọi thay vì tạo mới
    + Nếu activity được chưa tồn tạo một instance mới
- FLAG_ACTIVITY_NEW_TASK:
    + Nếu một task đã chạy cho activity mà được gọi, thì activity mới sẽ không được bắt đầu,
        thay vào đó, task hiện tại sẽ chỉ được đưa lên foreground với trạng thái của task trước đó
    + Nếu Sử dụng cờ FLAG_ACTIVITY_MULTIPLE_TASK luôn khởi chạy một task mới
     1. TaskID 1: A → B, gọi C với FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_MULTIPLE_TASK
     2. TaskID 1: A → B, TaskID 2: C
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<TextView>(R.id.tvTittle).text = "Activity A \n taskId: ${this.taskId} \n instanceId ${this.hashCode()}"
        findViewById<Button>(R.id.btnA).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<Button>(R.id.btnB).setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java))
        }
        findViewById<Button>(R.id.btnC).setOnClickListener {
            startActivity(Intent(this, ActivityC::class.java))
        }
        findViewById<Button>(R.id.btnD).setOnClickListener {
            startActivity(Intent(this, ActivityD::class.java))
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("Main onNewIntent", "onNewIntent: ")
    }
}

class ActivityB : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tvTittle).text = "Activity B \n taskId: ${this.taskId} \n instanceId ${this.hashCode()}"
        findViewById<Button>(R.id.btnA).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<Button>(R.id.btnB).setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java))
        }
        findViewById<Button>(R.id.btnC).setOnClickListener {
            startActivity(Intent(this, ActivityC::class.java))
        }
        findViewById<Button>(R.id.btnD).setOnClickListener {
            startActivity(Intent(this, ActivityD::class.java))
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("B onNewIntent", "onNewIntent: ")
    }
}

class ActivityC : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tvTittle).text = "Activity C \n taskId: ${this.taskId} \n instanceId ${this.hashCode()}"
        findViewById<Button>(R.id.btnA).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<Button>(R.id.btnB).setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java))
        }
        findViewById<Button>(R.id.btnC).setOnClickListener {
            startActivity(Intent(this, ActivityC::class.java))
        }
        findViewById<Button>(R.id.btnD).setOnClickListener {
            startActivity(Intent(this, ActivityD::class.java))
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("C onNewIntent", "onNewIntent: ")
    }
}

class ActivityD : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tvTittle).text = "Activity D \n taskId: ${this.taskId} \n instanceId ${this.hashCode()}"
        findViewById<Button>(R.id.btnA).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<Button>(R.id.btnB).setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java))
        }
        findViewById<Button>(R.id.btnC).setOnClickListener {
            startActivity(Intent(this, ActivityC::class.java))
        }
        findViewById<Button>(R.id.btnD).setOnClickListener {
            startActivity(Intent(this, ActivityD::class.java))
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("D onNewIntent", "onNewIntent: ")
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
        Row(
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
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = firstActionText,
                modifier = Modifier,
                fontSize = 18.pxToDp().toSp(),
                lineHeight = 26.pxToDp().toSp(),
                color = Color.White,
            )
            if (firstActionText == "Continue"){
                Spacer(modifier = Modifier.width(16.pxToDp()))
                Icon(
                    painter = painterResource(R.drawable.ic_popup_next),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(12.pxToDp()))
        Row(
            modifier = Modifier
                .width(276.pxToDp())
                .height(44.pxToDp())
                .clip(RoundedCornerShape(999.pxToDp()))
                .background(Color(0xFF4A0A82)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = firstActionText,
                modifier = Modifier,
                fontSize = 18.pxToDp().toSp(),
                lineHeight = 26.pxToDp().toSp(),
                color = Color.White,
            )
            if (secondActionText == "Continue"){
                Spacer(modifier = Modifier.width(16.pxToDp()))
                Icon(
                    painter = painterResource(R.drawable.ic_popup_next),
                    contentDescription = null,
                    tint = Color.White
                )
            }
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

@Composable
fun SelectImageScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (

        ) {

        }
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
        ) {

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

@Preview(showBackground = true)
@Composable
fun Preview() {
    FlowCoroutineTheme {
        Popup(
            "Hands Unrecognized",
            "We were unable to recognize the hands in the image. Please try again with a different image.",
            "Choose Another Image",
            "Cancel"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButton() {
    FlowCoroutineTheme {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { /*TODO*/ }, // Bo góc ít hơn mặc định
        ) {
            Text("Less Rounde123333323 1231231231212312312312312312312312312312312312312312312312312312312312312312323333d Button")
        }
    }
}
