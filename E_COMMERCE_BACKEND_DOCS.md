# Tài Liệu Thiết Kế RESTful API - Dự Án E-Commerce Backend (Spring Boot)

Tài liệu này mô tả chi tiết các RESTful API endpoints cho dự án E-Commerce (Thương mại điện tử) xây dựng bằng Java Spring Boot.

## 🛠 Công Nghệ Sử Dụng (Tech Stack)
- **Framework:** Java 17+, Spring Boot 3+ (Spring Web, Spring Data JPA, Spring Security, Spring Validation).
- **Database:** MySQL hoặc PostgreSQL.
- **Authentication:** JWT (JSON Web Token).
- **API Documentation:** Swagger / OpenAPI 3.
- **Base URL:** `/api/v1`

---

## 1. Authentication (Xác Thực)

### 1.1 Đăng ký tài khoản mới
- **Endpoint:** `/api/v1/auth/register`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "email": "user@example.com",
    "password": "Password123!",
    "firstName": "Nguyen",
    "lastName": "Van A",
    "phoneNumber": "0987654321"
  }
  ```
- **Response:** (201 Created)
  ```json
  {
    "message": "Đăng ký thành công!",
    "data": {
      "id": 1,
      "email": "user@example.com",
      "firstName": "Nguyen",
      "lastName": "Van A"
    }
  }
  ```

### 1.2 Đăng nhập (Login)
- **Endpoint:** `/api/v1/auth/login`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "email": "user@example.com",
    "password": "Password123!"
  }
  ```
- **Response:** (200 OK)
  ```json
  {
    "message": "Đăng nhập thành công!",
    "data": {
      "accessToken": "eyJhbGciOiJIUzI1NiIsInR...",
      "refreshToken": "def502005085e3d74c0c...",
      "tokenType": "Bearer",
      "expiresIn": 3600
    }
  }
  ```

---

## 2. User Profile (Thông Tin Người Dùng)
*Yêu cầu Header: `Authorization: Bearer <accessToken>`*

### 2.1 Lấy thông tin cá nhân
- **Endpoint:** `/api/v1/users/me`
- **Method:** `GET`
- **Response:** (200 OK)
  ```json
  {
    "data": {
      "id": 1,
      "email": "user@example.com",
      "firstName": "Nguyen",
      "lastName": "Van A",
      "phoneNumber": "0987654321",
      "address": "123 Đường ABC, Quận 1, TP HCM",
      "avatar": "https://example.com/avatar.jpg"
    }
  }
  ```

### 2.2 Cập nhật thông tin cá nhân
- **Endpoint:** `/api/v1/users/me`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "firstName": "Nguyen",
    "lastName": "Van B",
    "phoneNumber": "0111222333",
    "address": "456 Đường XYZ, Quận 2, TP HCM"
  }
  ```
- **Response:** (200 OK - Trả về thông tin user đã cập nhật)

---

## 3. Product (Sản Phẩm)

### 3.1 Lấy danh sách sản phẩm (Có phân trang, lọc và tìm kiếm)
- **Endpoint:** `/api/v1/products`
- **Method:** `GET`
- **Query Parameters:**
  - `page` (default: 0)
  - `size` (default: 10)
  - `search` (từ khóa tìm kiếm)
  - `categoryId` (lọc theo danh mục)
  - `sortBy` (price_asc, price_desc, new_arrival)
- **Response:** (200 OK)
  ```json
  {
    "data": {
      "content": [
        {
          "id": 101,
          "name": "Laptop Dell XPS 15",
          "description": "Laptop cao cấp dành cho doanh nhân...",
          "price": 35000000,
          "stockQuantity": 50,
          "category": { "id": 1, "name": "Laptop" },
          "imageUrl": "https://example.com/laptop.png"
        }
      ],
      "pageNumber": 0,
      "pageSize": 10,
      "totalElements": 150,
      "totalPages": 15
    }
  }
  ```

### 3.2 Lấy chi tiết một sản phẩm
- **Endpoint:** `/api/v1/products/{id}`
- **Method:** `GET`
- **Response:** (200 OK - Trả về chi tiết sản phẩm và danh sách ảnh/review)

---

## 4. Cart (Giỏ Hàng)
*Yêu cầu Header: `Authorization: Bearer <accessToken>`*

### 4.1 Lấy thông tin giỏ hàng
- **Endpoint:** `/api/v1/cart`
- **Method:** `GET`
- **Response:** (200 OK)
  ```json
  {
    "data": {
      "id": 1,
      "userId": 1,
      "totalPrice": 70000000,
      "items": [
        {
          "cartItemId": 1,
          "productId": 101,
          "productName": "Laptop Dell XPS 15",
          "price": 35000000,
          "quantity": 2,
          "subTotal": 70000000
        }
      ]
    }
  }
  ```

### 4.2 Thêm sản phẩm vào giỏ hàng
- **Endpoint:** `/api/v1/cart/items`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "productId": 101,
    "quantity": 1
  }
  ```
- **Response:** (200 OK - Trả về giỏ hàng mới nhất)

### 4.3 Cập nhật số lượng sản phẩm trong giỏ hàng
- **Endpoint:** `/api/v1/cart/items/{cartItemId}`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "quantity": 3
  }
  ```
- **Response:** (200 OK)

### 4.4 Xóa sản phẩm khỏi giỏ hàng
- **Endpoint:** `/api/v1/cart/items/{cartItemId}`
- **Method:** `DELETE`
- **Response:** (204 No Content)

---

## 5. Order (Đơn Hàng)
*Yêu cầu Header: `Authorization: Bearer <accessToken>`*

### 5.1 Tạo đơn hàng mới (Checkout từ giỏ hàng)
- **Endpoint:** `/api/v1/orders`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "shippingAddress": "123 Đường ABC, Quận 1, TP HCM",
    "phoneNumber": "0987654321",
    "paymentMethod": "CREDIT_CARD",
    "notes": "Giao giờ hành chính"
  }
  ```
- **Response:** (201 Created)
  ```json
  {
    "message": "Tạo đơn hàng thành công!",
    "data": {
      "orderId": "ORD-12345",
      "status": "PENDING",
      "totalAmount": 70000000,
      "paymentMethod": "CREDIT_CARD",
      "createdAt": "2026-05-08T18:00:27Z"
    }
  }
  ```

### 5.2 Lấy danh sách đơn hàng của tôi
- **Endpoint:** `/api/v1/orders/my-orders`
- **Method:** `GET`
- **Response:** (200 OK - Danh sách các đơn hàng đã đặt)

### 5.3 Xem chi tiết đơn hàng
- **Endpoint:** `/api/v1/orders/{orderId}`
- **Method:** `GET`
- **Response:** (200 OK)

---

## 6. Payment (Thanh Toán)
*Thường tích hợp với các cổng như VNPay, MoMo, Stripe...*

### 6.1 Tạo URL thanh toán
- **Endpoint:** `/api/v1/payments/create`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "orderId": "ORD-12345",
    "amount": 70000000,
    "provider": "VNPAY"
  }
  ```
- **Response:** (200 OK)
  ```json
  {
    "data": {
      "paymentUrl": "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?..."
    }
  }
  ```

### 6.2 IPN / Webhook xử lý thanh toán (Server to Server)
- **Endpoint:** `/api/v1/payments/callback/{provider}`
- **Method:** `POST` hoặc `GET` (tùy Provider)
- **Description:** Cổng thanh toán gọi API này để báo kết quả thanh toán. Server sẽ update trạng thái Order (PAID / FAILED).

---

## 7. Các Mã Lỗi (Error Handling Convention)

Hệ thống sử dụng format chung cho các exception (Global Exception Handling với `@ControllerAdvice`):

```json
{
  "timestamp": "2026-05-08T18:00:27Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Email đã được sử dụng!",
  "path": "/api/v1/auth/register"
}
```

### Các HTTP Status Code thường dùng:
- `200 OK`: Thành công.
- `201 Created`: Tạo mới thành công.
- `204 No Content`: Cập nhật/xóa thành công không trả về body.
- `400 Bad Request`: Lỗi tham số, dữ liệu không hợp lệ.
- `401 Unauthorized`: Lỗi xác thực (chưa login, token hết hạn).
- `403 Forbidden`: Lỗi phân quyền (không có quyền truy cập).
- `404 Not Found`: Không tìm thấy tài nguyên.
- `500 Internal Server Error`: Lỗi logic server.
