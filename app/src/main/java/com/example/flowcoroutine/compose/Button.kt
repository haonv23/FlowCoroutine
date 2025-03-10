package com.example.flowcoroutine.compose

/*
# Ghi Chép Kỹ Thuật Jetpack Compose

## Thành phần: [Button]

### 1. Định nghĩa & Mục đích sử dụng
Button là thành phần UI cơ bản trong Jetpack Compose dùng để tạo nút bấm có thể tương tác.
Nó hiển thị văn bản, có thể hiển thị icon, có 2 trạng thái enabled/disabled
và hỗ trợ xử lý sự kiện nhấn.
```

### 2. Cú pháp cơ bản
@Composable
fun Button(
    onClick: () -> Unit, Hàm xử lý sự kiện nhấn
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    // Triển khai nội bộ
}
```

### 3. Tham số quan trọng
| Tham số | Kiểu | Mô tả | Mặc định |
|---------|------|-------|----------|
| onclick | unit | được xảy ra khi bấm vào button | không có |
| shape | | Hình dạng của button | shaperound(12.dp) |
### 4. Cách sử dụng cơ bản & Ví dụ
@Composable
fun DemoButton() {
    Button(
        onClick = { /* Xử lý sự kiện click */ }
    ) {
        Text("Nhấn vào đây")
    }
}

// Ví dụ với icon
@Composable
fun IconButtonDemo() {
    Button(
        onClick = { /* Xử lý sự kiện click */ }
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text("Yêu thích")
    }
}

### 5. Các biến thể & Tùy chỉnh phổ biến
    1. ElevatedButton, mặc định có bóng đổ
        ElevatedButton(onClick = {}) {
            Text("Elevated Button")
        }
    2. OutlinedButton, mặc địng có viền, không nền
        OutlinedButton(onClick = {}) {
            Text("Outlined Button")
        }
    3. TextButton, mặc định không có viền, không bóng
        TextButton(onClick = {}) {

### 6. Kết hợp với Modifier
// Các modifier phổ biến với thành phần này
    Button(
        onClick = { /* Xử lý sự kiện click */ },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(56.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
    ) {
        Text("Button với Modifier")
    }
```

### 7. State management
@Composable
fun ButtonWithState() {
    var isLoading by remember { mutableStateOf(false) }
    Button(
        onClick = {
            // Cập nhật state khi button được nhấn
            isLoading = true
            // Giả lập một tác vụ
            // Trong thực tế, nên sử dụng coroutine và viewModelScope
        },
        enabled = !isLoading
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colors.onPrimary
            )
        } else {
            Text("Đăng nhập")
        }
    }
}

### 8. Vấn đề thường gặp & Cách giải quyết
| Vấn đề | Nguyên nhân | Giải pháp |
|--------|-------------|-----------|
1. |callback được onclick tạo lại | lý do: Recomposition |
đưa callback vào val onclick = remember{ viewmodel.callback }}|
2. |lỗi logic..| lý do: nút được bấm nhiều lần liên tiếp |
enabled hợp lý|


### 9. Performance tips
Tip 1: Sử dụng remember cho các lambda callback để tránh tạo mới mỗi khi recomposition
Tip 2: Với button có nội dung phức tạp, sử dụng các Composable function riêng biệt và tách biệt logic
Tip 3: Tái sử dụng lambda với một giao diện nhiều button
Tip 4: Sử dụng derivedStateOf cho các điều kiện phức tạp ảnh hưởng đến trạng thái enabled

### 10. Code thực tế từ dự án
```kotlin
// Code thực tế đã sử dụng trong dự án
@Composable
fun ProjectExample() {
    // Triển khai thực tế
}
```

### 11. So sánh với View truyền thống
| Tiêu chí | Compose | XML/View |
|----------|---------|----------|
| Quản lý sự kiện| ngay trong compose| phải ở activity, fragment thông qua inflate |
|Cú pháp | chỉ cần kotlin | xml và kotlin |
|Chỉnh sửa giao diện | dễ dàng thông qua modifier | khó khăn hơn, background nếu muốn phải định nghĩa riêng một file xml|

### 12. Ghi chú & Kinh nghiệm cá nhân
```
- Khi xử lý form, nên sử dụng remember hoặc derivedStateOf để tính toán trạng thái enabled của button
- Nên sử dụng các biến thể có sẵn như OutlinedButton, TextButton thay vì tùy chỉnh Button thông thường quá nhiều
- Để tạo button tùy chỉnh cao, có thể kết hợp Surface hoặc Box với clickable modifier thay vì Button,
nhưng clickable có nền xám khi click
 */