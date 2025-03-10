package com.example.flowcoroutine.couroutine

/*
2. Scope trong Coroutine là gì? Liệt kê các loại scope phổ biến trong Android.
    Scope trong Coroutine là phạm vi tồn tại của coroutine, định nghĩa thời điểm coroutine được khởi động
    và hủy. Mỗi coroutine phải được khởi chạy trong một scope cụ thể, giúp quản lý vòng đời và tránh rò
    rỉ bộ nhớ.
    * Các Scope:
    - viewModelScope
        + Gắn với vòng đời của ViewModel
        +Tự động hủy khi ViewModel bị xóa
        + Sử dụng: viewModelScope.launch { ... }

    - lifecycleScope
        + Gắn với vòng đời của Lifecycle Owner (Activity, Fragment)
        + Tự động hủy khi lifecycle chuyển sang trạng thái DESTROYED
        + Sử dụng: lifecycleScope.launch { ... }

    - GlobalScope
        + Không gắn với bất kỳ thành phần nào
        + Tồn tại trong suốt vòng đời của ứng dụng, không tự động hủy, cần quản lý thủ công
        + Sử dụng: GlobalScope.launch { ... }

    - CoroutineScope tự tạo
        + Tạo scope với context tùy chỉnh
        + Sử dụng: val scope = CoroutineScope(Dispatchers.IO + Job())
        + Cần gọi scope.cancel() khi không cần nữa

    - viewLifecycleOwner.lifecycleScope
        + Gắn với vòng đời view của Fragment
        + Tự động hủy khi view bị hủy
        + Sử dụng: viewLifecycleOwner.lifecycleScope.launch { ... }

    - scopedCoroutineScope()
        + Trong Jetpack Compose
        + Gắn với composable function
        + Tự động hủy khi composable không còn trong composition

    - rememberCoroutineScope()
        + Trong Jetpack Compose
        + Được ghi nhớ qua các lần recomposition
        + Hủy khi composable rời khỏi composition
*/
