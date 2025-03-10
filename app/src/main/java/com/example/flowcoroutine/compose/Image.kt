package com.example.flowcoroutine.compose

/*
# Ghi Chép Kỹ Thuật Jetpack Compose

## Thành phần: [Image]

### 1. Định nghĩa & Mục đích sử dụng
Image là thành phần UI trong Jetpack Compose dùng để hiển thị hình ảnh từ nhiều nguồn khác nhau
như resource, drawable, vector

### 2. Cú pháp cơ bản

@Composable
fun DemoImage() {
    Image(
        painter: Painter,
        contentDescription: String?,
        modifier: Modifier = Modifier,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Fit,
        alpha: Float = 1.0f,
        colorFilter: ColorFilter? = null
    )
}
```

### 3. Tham số quan trọng
| Tham số | Kiểu | Mô tả | Mặc định |
|---------|------|-------|----------|
| painter | Painter | Nguồn hình ảnh | - |
| contentDescription | String? | Mô tả cho accessibility | - |
| contentScale | ContentScale | Cách ảnh được scale để vừa với không gian | ContentScale.Fit |
| alignment | Alignment | Căn chỉnh hình ảnh trong không gian | Alignment.Center |
| colorFilter | ColorFilter? | Bộ lọc màu áp dụng lên hình ảnh | null |

### 4. Cách sử dụng cơ bản & Ví dụ

@Composable
fun DemoImage() {
    Image(
        painter = painterResource(id = R.drawable.sample_image),
        contentDescription = "Mô tả hình ảnh"
    )
}
```

### 5. Các biến thể & Tùy chỉnh phổ biến

// 1. Hình ảnh với viền tròn
Image(
    painter = painterResource(id = R.drawable.profile_pic),
    contentDescription = "Ảnh hồ sơ",
    modifier = Modifier
        .size(100.dp)
        .clip(CircleShape)
        .border(2.dp, Color.Gray, CircleShape)
)

// 2. Hình ảnh với hiệu ứng mờ
Image(
    painter = painterResource(id = R.drawable.background),
    contentDescription = "Nền mờ",
    modifier = Modifier.fillMaxSize(),
    contentScale = ContentScale.Crop,
    alpha = 0.6f
)

// 3. Hình ảnh với bộ lọc màu
Image(
    painter = painterResource(id = R.drawable.icon),
    contentDescription = "Icon với bộ lọc",
    colorFilter = ColorFilter.tint(Color.Blue)
)
```

### 6. Kết hợp với Modifier

// Các modifier phổ biến với thành phần này
Image(
    painter = painterResource(id = R.drawable.landscape),
    contentDescription = "Phong cảnh",
    modifier = Modifier
        .padding(16.dp)
        .height(200.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .shadow(4.dp)
)
```

### 7. State management

@Composable
fun ImageWithState() {
    var isExpanded by remember { mutableStateOf(false) }
    
    Image(
        painter = painterResource(id = R.drawable.sample_image),
        contentDescription = "Hình ảnh có thể mở rộng",
        modifier = Modifier
            .clickable { isExpanded = !isExpanded }
            .size(if (isExpanded) 300.dp else 150.dp)
            .animateContentSize()
    )
}
```

### 8. Vấn đề thường gặp & Cách giải quyết
| Vấn đề | Nguyên nhân | Giải pháp |
|--------|-------------|-----------|
|ANR | Tải ảnh độ phân giải cao | Sử dụng thư viện như Coil, Glide, giảm độ phân giải, xử lý lỗi.. |
| Ảnh bị méo | ContentScale không phù hợp | Chọn ContentScale.Crop hoặc FillBounds tùy nhu cầu |

### 9. Performance tips
- Tip 1: Sử dụng thư viện tải ảnh như Coil, Glide
- Tip 2: Tối ưu hóa kích thước ảnh trong drawable resources, giảm độ phân giải bằng cách chuyển về webp
- Tip 3: Sử dụng placeholder, error trong khi tải ảnh từ mạng

### 10. So sánh với View truyền thống
| Tiêu chí | Compose | XML/View |
|----------|---------|----------|
| Tải ảnh từ mạng | Cần thư viện như Coil | Cần thư viện như Glide, Picasso |
| Hiệu ứng | Dễ dàng với Modifier | Yêu cầu các lớp bao bọc hoặc thư viện |

### 11. Ghi chú & Kinh nghiệm cá nhân
- Luôn cung cấp contentDescription có ý nghĩa
- Sử dụng AsyncImage từ Coil hoặc GlideImage để tải ảnh mạng hiệu quả
- Với ảnh lớn, chất lượng cao, cần cân nhắc giảm độ phân giải
*/