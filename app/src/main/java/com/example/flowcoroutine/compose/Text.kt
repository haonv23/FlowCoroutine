package com.example.flowcoroutine.compose

/*
# Ghi Chép Kỹ Thuật Jetpack Compose

## Thành phần: [Text]

### 1. Định nghĩa & Mục đích sử dụng
Text là thành phần UI cơ bản trong Jetpack Compose dùng để hiển thị văn bản.
Nó cung cấp nhiều tùy chọn định dạng như font chữ, kích thước, màu sắc, kiểu dáng,
và hỗ trợ đầy đủ các tính năng như text overflow, text alignment, và text decoration.

### 2. Cú pháp cơ bản
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    // Triển khai nội bộ
}
```

### 3. Tham số quan trọng
| Tham số | Kiểu | Mô tả | Mặc định |
|---------|------|-------|----------|
| text | String | Nội dung văn bản hiển thị | - |
| color | Color | Màu sắc của văn bản | Color.Unspecified |
| fontSize | TextUnit | Kích thước chữ | TextUnit.Unspecified |
| fontWeight | FontWeight | Độ đậm của chữ | null |
| maxLines | Int | Số dòng tối đa hiển thị | Int.MAX_VALUE |
| overflow | TextOverflow | Xử lý khi văn bản vượt quá không gian | TextOverflow.Clip |

### 4. Cách sử dụng cơ bản & Ví dụ
@Composable
fun DemoText() {
    Text("Đây là văn bản cơ bản")
}

// Ví dụ với định dạng
@Composable
fun FormattedTextDemo() {
    Text(
        text = "Văn bản được định dạng",
        color = Color.Blue,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}
```

### 5. Các biến thể & Tùy chỉnh phổ biến
```
var text by remember { mutableStateOf("") }

TextField(
    value = text,
    onValueChange = { text = it },
    label = { Text("Enter text") }
)
```

### 6. Kết hợp với Modifier
```
// Các modifier phổ biến với thành phần này
Text(
    text = "Text với Modifier",
    modifier = Modifier
        .padding(16.dp)
        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
        .padding(8.dp)
        .fillMaxWidth()
)
```

### 7. State management
```
@Composable
fun TextWithState() {
    var text by remember { mutableStateOf("Văn bản ban đầu") }
    
    Column {
        Text(text)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            text = "Văn bản đã thay đổi"
        }) {
            Text(text = text)
        }
    }
}
```

### 8. Vấn đề thường gặp & Cách giải quyết
| Vấn đề | Nguyên nhân | Giải pháp |
|--------|-------------|-----------|
| Văn bản bị cắt | Không gian không đủ | Sử dụng maxLines và overflow |
| Kích thước không đồng nhất | Không sử dụng theme | Áp dụng desgin system|
| Không hiển thị đúng font | Font chưa được đăng ký | Thêm font vào dự án và khai báo FontFamily |

### 9. Performance tips
- Tip 1: Tránh sử dụng String template phức tạp trong Text để giảm recomposition

### 10. So sánh với View truyền thống
| Tiêu chí | Compose | XML/View |
|----------|---------|----------|
| Định dạng | Trực tiếp trong code  | XML + setTextColor(), setTextSize() |
| Văn bản động | Dễ dàng với state | Yêu cầu setText() và findViewById() |
| Hiệu suất | Tối ưu hóa recomposition | Yêu cầu ViewHolder cho hiệu suất danh sách |

### 11. Ghi chú & Kinh nghiệm cá nhân
- Với văn bản có thể click, sử dụng clickable modifier
*/