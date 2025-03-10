package com.example.flowcoroutine.compose

/*

# Ghi Chép Kỹ Thuật Jetpack Compose

## Thành phần: [Spacer]

### 1. Định nghĩa & Mục đích sử dụng
Spacer là thành phần UI đơn giản trong Jetpack Compose dùng để tạo khoảng trống giữa các thành phần khác.
Nó không hiển thị bất kỳ nội dung nào và chỉ chiếm không gian theo kích thước được chỉ định thông qua modifier.
Spacer thường được sử dụng để tạo khoảng cách đều giữa các phần tử trong Row, Column.

### 2. Cú pháp cơ bản

Spacer(
    modifier: Modifier
)
```

### 3. Tham số quan trọng
| Tham số | Kiểu | Mô tả | Mặc định |
|---------|------|-------|----------|
| modifier | Modifier | Xác định kích thước và thuộc tính khác | - |

### 4. Cách sử dụng cơ bản & Ví dụ

@Composable
fun DemoSpacer() {
    Column {
        Text("Dòng văn bản thứ nhất")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Dòng văn bản thứ hai")
    }
}

// Ví dụ với Row
@Composable
fun HorizontalSpacerDemo() {
    Row {
        Icon(Icons.Default.Home, contentDescription = "Home")
        Spacer(modifier = Modifier.width(8.dp))
        Text("Trang chủ")
    }
}
```

### 5. Các biến thể & Tùy chỉnh phổ biến

// 1. Spacer linh hoạt (weight)
Row(modifier = Modifier.fillMaxWidth()) {
    Text("Bên trái")
    Spacer(modifier = Modifier.weight(1f))
    Text("Bên phải")
}

// 2. Spacer với kích thước tùy chỉnh
Column {
    Text("Tiêu đề", style = MaterialTheme.typography.h5)
    Spacer(modifier = Modifier.height(24.dp))
    Text("Nội dung", style = MaterialTheme.typography.body1)
}

// 3. Spacer với màu nền (đường ngăn cách)
Column {
    Text("Phần 1")
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.LightGray)
            .padding(vertical = 8.dp)
    )
    Text("Phần 2")
}
```

### 6. Kết hợp với Modifier

// Các modifier phổ biến với thành phần này
Spacer(
    modifier = Modifier
        .height(16.dp)
        .fillMaxWidth()
)

// Spacer với weight
Row(modifier = Modifier.fillMaxWidth()) {
    Button(onClick = { /* Action 1 */ }) {
        Text("Nút 1")
    }
    Spacer(modifier = Modifier.weight(1f))
    Button(onClick = { /* Action 2 */ }) {
        Text("Nút 2")
    }
}
```

### 7. State management

@Composable
fun SpacerWithState() {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text("Tiêu đề")
        Spacer(
            modifier = Modifier.height(
                if (expanded) 32.dp else 8.dp
            )
        )
        Text("Nội dung")

        Button(onClick = { expanded = !expanded }) {
            Text(if (expanded) "Thu gọn" else "Mở rộng")
        }
    }
}
```

### 8. Vấn đề thường gặp & Cách giải quyết
| Vấn đề | Nguyên nhân | Giải pháp |
|--------|-------------|-----------|
| Layout không như mong đợi | Sử dụng sai hướng | Đảm bảo dùng height trong Column, width trong Row |
| Spacer với weight không hoạt động | Parent không có fillMaxWidth/Height | Thêm fillMaxWidth/Height cho parent |

### 9. Performance tips
- Tip 1: Ưu tiên sử dụng arrangement trong Row/Column thay vì nhiều Spacer
- Tip 2: Spacer đơn giản hơn Divider cho khoảng trống không cần hiển thị đường kẻ
- Tip 3: Sử dụng remember cho giá trị kích thước động để tránh tạo lại trong recomposition

### 10. So sánh với View truyền thống
| Tiêu chí | Compose | XML/View |
|----------|---------|----------|
| Tạo khoảng cách | Spacer với height/width | Margin, padding hoặc Space widget |
| Tính linh hoạt | Có thể kết hợp với các modifier khác | Giới hạn với thuộc tính layout |
| Hiệu suất | Nhẹ, không có nội dung | Tương tự, là View trống |
| Responsive | Dễ dàng với modifier.weight | Yêu cầu sử dụng layout_weight |

### 11. Ghi chú & Kinh nghiệm cá nhân
- Tận dụng cách tiếp cận "ít code hơn" bằng cách sử dụng verticalArrangement và horizontalArrangement trong Column và Row
- Sử dụng Spacer.weight(1f) là cách đơn giản để đẩy các thành phần UI đến các cạnh của layout
- Spacer có thể thay thế Divider khi không cần hiển thị đường kẻ, giúp code dễ đọc hơn

*/