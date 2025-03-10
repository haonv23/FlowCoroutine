package com.example.flowcoroutine.compose

/*
# Ghi Chép Kỹ Thuật Jetpack Compose

## Thành phần: [Icon]

### 1. Định nghĩa & Mục đích sử dụng
Icon là thành phần UI trong Jetpack Compose dùng để hiển thị biểu tượng vector đơn giản.
Nó thường được sử dụng trong các thành phần UI như Button, TopAppBar, NavigationItem
và có thể tùy chỉnh kích thước, màu sắc dễ dàng theo thiết kế Material.

### 2. Cú pháp cơ bản

Icon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
)

// Hoặc với Painter
Icon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
)
```

### 3. Tham số quan trọng
| Tham số | Kiểu | Mô tả | Mặc định |
|---------|------|-------|----------|
| imageVector/painter | ImageVector/Painter | Nguồn biểu tượng | - |
| contentDescription | String? | Mô tả cho accessibility | - |
| tint | Color | Màu của biểu tượng | LocalContentColor.current |

### 4. Cách sử dụng cơ bản & Ví dụ

@Composable
fun DemoIcon() {
    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = "Yêu thích"
    )
}

// Ví dụ với Painter
@Composable
fun CustomIconDemo() {
    Icon(
        painter = painterResource(id = R.drawable.custom_icon),
        contentDescription = "Biểu tượng tùy chỉnh",
        tint = Color.Red
    )
}
```

### 5. Các biến thể & Tùy chỉnh phổ biến

// 1. Icon với kích thước tùy chỉnh
Icon(
    imageVector = Icons.Default.Settings,
    contentDescription = "Cài đặt",
    modifier = Modifier.size(36.dp)
)

// 2. Icon có thể nhấn
Icon(
    imageVector = Icons.Filled.Add,
    contentDescription = "Thêm",
    modifier = Modifier
        .clickable { /* Xử lý sự kiện click */ }
        .padding(8.dp)
        .size(24.dp),
    tint = MaterialTheme.colors.primary
)

// 3. Icon với hiệu ứng nổi
Icon(
    imageVector = Icons.Default.Share,
    contentDescription = "Chia sẻ",
    modifier = Modifier
        .padding(8.dp)
        .shadow(4.dp, CircleShape)
        .background(MaterialTheme.colors.surface, CircleShape)
        .padding(8.dp)
)
```

### 6. Kết hợp với Modifier

// Các modifier phổ biến với thành phần này
Icon(
    imageVector = Icons.Default.Notifications,
    contentDescription = "Thông báo",
    modifier = Modifier
        .padding(8.dp)
        .background(Color.LightGray, CircleShape)
        .padding(8.dp)
        .size(24.dp),
    tint = Color.Red
)
```

### 7. State management

@Composable
fun IconWithState() {
    var isFavorite by remember { mutableStateOf(false) }
    
    Icon(
        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
        contentDescription = if (isFavorite) "Đã yêu thích" else "Chưa yêu thích",
        modifier = Modifier
            .clickable { isFavorite = !isFavorite }
            .padding(8.dp),
        tint = if (isFavorite) Color.Red else Color.Gray
    )
}
```

### 8. Vấn đề thường gặp & Cách giải quyết
| Vấn đề | Nguyên nhân | Giải pháp |
|--------|-------------|-----------|
| Icon không nhấn được | Thiếu modifier clickable | Thêm .clickable và thiết lập kích thước vùng nhấn |

### 9. Performance tips


### 10. So sánh với View truyền thống
| Tiêu chí | Compose | XML/View |
|----------|---------|----------|
| Nguồn icon | ImageVector hoặc Painter | Drawable resources |
| Tint | Tham số trực tiếp | setColorFilter, ImageView.setTint |
| Kích thước | Modifier.size | layout_width, layout_height |

### 11. Ghi chú & Kinh nghiệm cá nhân
- Luôn cung cấp contentDescription có ý nghĩa cho accessibility
- Sử dụng kích thước phù hợp với (24dp là tiêu chuẩn)
- Kết hợp với IconButton khi cần tạo nút icon có ripple effect
*/