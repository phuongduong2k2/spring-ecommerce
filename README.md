# Tài liệu Thiết kế API & Dữ liệu - Backend E-Commerce (Nâng cao)

Tài liệu này mô tả kiến trúc dữ liệu và API cho một hệ thống Backend E-commerce phức tạp (hỗ trợ Multi-vendor, Biến thể sản phẩm, Quản lý Kho, Khuyến mãi, và Vận chuyển).

---

## 1. Yêu cầu Dữ liệu (Advanced Data Models)

Để hệ thống hoạt động thực tế và đáp ứng nghiệp vụ phức tạp, chúng ta cần chia nhỏ và bổ sung các Entities sau:

### 1.1. User & Cấu hình (Identity & Access Management)
*   **User:** Quản lý tài khoản và định danh.
    *   `id`, `email`, `password`, `phone`, `role` (`USER`, `VENDOR`, `ADMIN`, `MODERATOR`), `status` (`ACTIVE`, `BANNED`, `UNVERIFIED`).
*   **User Profile:** Thông tin cá nhân chi tiết.
    *   `userId`, `firstName`, `lastName`, `avatarUrl`, `dob`, `gender`.
*   **Address:** Danh bạ địa chỉ.
    *   `id`, `userId`, `type` (`HOME`, `OFFICE`), `street`, `ward`, `district`, `city`, `isDefault`.

### 1.2. Product & Catalog (Quản lý Sản phẩm phức tạp)
*   **Category:** Danh mục đa cấp (Mô hình cây).
    *   `id`, `parentId`, `name`, `slug`, `icon`, `level`.
*   **Brand:** Thương hiệu.
    *   `id`, `name`, `logo`, `description`.
*   **Product:** Bảng sản phẩm gốc.
    *   `id`, `shopId`, `categoryId`, `brandId`, `name`, `slug`, `description`, `basePrice`, `status`.
*   **Product Attribute & Option (Biến thể sản phẩm):** Cho phép 1 sản phẩm có nhiều màu sắc/kích thước.
    *   `Attribute`: Bảng thuộc tính (VD: `id=1, name='Color'`, `id=2, name='Size'`).
    *   `AttributeOption`: Giá trị (VD: `Red`, `Blue`, `XL`, `XXL`).
*   **Product SKU (Stock Keeping Unit):** Đại diện cho một phân loại cụ thể của sản phẩm.
    *   `id`, `productId`, `skuCode`, `price`, `stockQuantity`, `attributes` (JSON: `{"Color":"Red", "Size":"XL"}`).
*   **Product Media:** Quản lý ảnh/video sản phẩm.
    *   `id`, `productId`, `mediaUrl`, `type` (`IMAGE`, `VIDEO`), `isThumbnail`.

### 1.3. Inventory & Warehouse (Quản lý Kho & Tồn kho)
*   **Warehouse:** Thông tin kho hàng.
    *   `id`, `shopId`, `name`, `address`.
*   **Inventory Transaction:** Lịch sử xuất/nhập kho (Sổ kho).
    *   `id`, `skuId`, `warehouseId`, `type` (`IMPORT`, `EXPORT`, `RETURN`), `quantity`, `note`, `createdAt`.

### 1.4. Promotion & Marketing (Khuyến mãi & Mã giảm giá)
*   **Coupon / Voucher:** Mã giảm giá.
    *   `id`, `shopId` (hoặc null nếu là voucher sàn), `code`, `discountType` (`PERCENTAGE`, `FIXED_AMOUNT`), `discountValue`, `minOrderValue`, `maxDiscount`, `startDate`, `endDate`, `usageLimit`.
*   **User Coupon:** Lưu ví voucher của người dùng.
    *   `id`, `userId`, `couponId`, `isUsed`, `usedAt`.

### 1.5. Đặt hàng & Vận chuyển (Order & Shipping)
*   **Cart & CartItem:** Giỏ hàng (nhóm theo Shop).
    *   `Cart`: `id`, `userId`
    *   `CartItem`: `id`, `cartId`, `skuId`, `quantity`.
*   **Order:** Đơn hàng tổng. Khi user checkout nhiều shop, hệ thống tách thành các **Sub-Order**.
    *   `id`, `userId`, `totalAmount`, `discountAmount`, `shippingFee`, `finalAmount`, `status`.
*   **SubOrder (Shop Order):** Đơn hàng thuộc về 1 cửa hàng cụ thể.
    *   `id`, `parentOrderId`, `shopId`, `status` (`PENDING`, `CONFIRMED`, `PICKED_UP`, `SHIPPED`, `DELIVERED`, `CANCELLED`).
*   **OrderItem:** Chi tiết sản phẩm trong SubOrder.
    *   `id`, `subOrderId`, `skuId`, `price`, `quantity`.
*   **Shipment:** Đơn vị vận chuyển và Tracking.
    *   `id`, `subOrderId`, `carrier` (`GHN`, `GHTK`, `NinjaVan`), `trackingNumber`, `shippingStatus`, `estimatedDelivery`.

### 1.6. Thanh toán (Payment)
*   **Transaction:** Lịch sử giao dịch.
    *   `id`, `orderId`, `userId`, `amount`, `paymentGateway` (`VNPAY`, `MOMO`, `STRIPE`), `transactionRef` (Mã GD của đối tác), `status` (`SUCCESS`, `FAILED`, `PENDING`), `createdAt`.

### 1.7. Tương tác (Interaction)
*   **Review & Rating:** Đánh giá sản phẩm.
    *   `id`, `userId`, `productId`, `orderId`, `rating`, `comment`, `mediaUrls` (List<String>).
*   **Wishlist:** Sản phẩm yêu thích.
    *   `id`, `userId`, `productId`.
*   **Notification:** Thông báo hệ thống.
    *   `id`, `userId`, `title`, `content`, `type` (`ORDER_UPDATE`, `PROMOTION`, `SYSTEM`), `isRead`.

---

## 2. Authentication & Security Nâng cao

*   **JWT & Refresh Token:**
    *   `AccessToken` (Sống ngắn: 15-30 phút).
    *   `RefreshToken` (Sống dài: 7-30 ngày), lưu trong HttpOnly Cookie để bảo mật hoặc lưu vào Database để cấp quyền revoke (thu hồi) thiết bị đăng nhập.
*   **OAuth2 (Social Login):** Tích hợp Đăng nhập bằng Google / Facebook / Apple.
*   **OTP Verification:** Xác thực số điện thoại hoặc email khi đăng ký mới / quên mật khẩu.
*   **Rate Limiting & Throttling:** Chống Spam Request, đặc biệt ở các API public như Login, Send OTP.

---

## 3. Danh sách API Endpoints Phức tạp

### 3.1. Authentication & Security
*   `POST /api/v1/auth/register` (Tạo tài khoản & Gửi mã OTP)
*   `POST /api/v1/auth/verify-otp` (Xác thực tài khoản)
*   `POST /api/v1/auth/login` (Trả về AccessToken & thiết lập RefreshToken Cookie)
*   `POST /api/v1/auth/refresh` (Cấp lại AccessToken mới)
*   `POST /api/v1/auth/logout` (Xóa RefreshToken ở DB/Cookie)
*   `GET /api/v1/auth/oauth2/google` (Login bằng Google)

### 3.2. Product Catalog (Sản phẩm & Tìm kiếm)
*   `GET /api/v1/products` (Tìm kiếm nâng cao: Full-text search, Filter theo giá, thương hiệu, đánh giá, danh mục, thuộc tính - Thường dùng **ElasticSearch**).
*   `GET /api/v1/products/{slug}` (Xem chi tiết sản phẩm + Danh sách SKUs/Biến thể).
*   `POST /api/v1/vendor/products` (Tạo sản phẩm mới, kèm JSON attributes cho các SKUs - Chỉ Vendor).
*   `GET /api/v1/categories/{slug}/tree` (Lấy cây danh mục).

### 3.3. Cart & Checkout (Quy trình mua hàng)
*   `GET /api/v1/cart` (Lấy giỏ hàng, tự động Gom nhóm sản phẩm theo Shop).
*   `POST /api/v1/cart/items` (Thêm SKU vào giỏ).
*   `POST /api/v1/checkout/calculate`
    *   **Mô tả:** API tính toán trước khi đặt hàng. Gửi danh sách cart items, mã Coupon, địa chỉ. Trả về tổng tiền hàng, tiền giảm giá, tiền phí ship dự kiến.
*   `POST /api/v1/checkout/place-order` (Tạo Order, SubOrders và OrderItems. Sử dụng **Database Transaction** để đảm bảo tính toàn vẹn (Rollback nếu lỗi)).

### 3.4. Payment (Thanh toán bên thứ 3)
*   `POST /api/v1/payments/create-url` (Tạo URL thanh toán VNPay/Momo và trả về cho Client chuyển hướng).
*   `GET /api/v1/payments/vnpay-return` (Callback URL khi người dùng thanh toán xong trên VNPay).
*   `POST /api/v1/payments/webhook` (Webhook nhận tín hiệu thanh toán thành công từ Gateway chạy ngầm).

### 3.5. Inventory (Quản lý Kho)
*   `POST /api/v1/vendor/inventory/import` (Nhập kho: Cộng số lượng SKU, ghi log vào Inventory Transaction).
*   `GET /api/v1/vendor/inventory/history` (Xem sổ kho).

### 3.6. Promotions (Khuyến mãi)
*   `POST /api/v1/coupons/apply` (Kiểm tra điều kiện áp dụng mã giảm giá cho Order).
*   `GET /api/v1/users/me/coupons` (Ví Voucher của tôi).

### 3.7. Order Management & Tracking
*   `GET /api/v1/orders/me` (Lịch sử đơn hàng của User).
*   `GET /api/v1/orders/{id}/tracking` (Hành trình đơn hàng).
*   `PATCH /api/v1/vendor/orders/{subOrderId}/status` (Vendor cập nhật trạng thái đơn: Chuẩn bị hàng -> Đã giao cho ĐVVC).

---

> [!TIP]
> **Gợi ý Kiến trúc:**
> Khi hệ thống phình to với các entity phức tạp này, việc sử dụng kiến trúc **Microservices** hoặc **Modular Monolith** sẽ rất hữu ích. Ví dụ: Tách Service `ProductCatalog`, `OrderManagement`, `Payment`, và `Inventory` thành các module độc lập.
> Ngoài ra, hệ thống thanh toán và trừ kho cần được thiết kế cẩn thận để tránh lỗi **Race Condition** (nhiều người cùng mua 1 sản phẩm gây âm kho) - có thể dùng cơ chế Database Locking (Pessimistic/Optimistic Lock) hoặc Redis.
