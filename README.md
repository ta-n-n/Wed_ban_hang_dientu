# Phân tích và cải thiện chất lượng mã nguồn với SonarQube
---

## 1. Cài đặt và cấu hình SonarQube & SonarScanner

### Cài đặt SonarQube

* Truy cập: [https://www.sonarsource.com/products/sonarqube/downloads/](https://www.sonarsource.com/products/sonarqube/downloads/)
* Tải bản **Community Edition** → giải nén thư mục
* Mở `StartSonar.bat` (Windows) tại: `D:\SonarScanner\sonarqube-25.7.0.110598\bin\windows-x86-64/StartSonar.bat`
* Truy cập trình duyệt: [http://localhost:9000](http://localhost:9000)
* Đăng nhập: `admin/admin` → đổi mật khẩu
* Tạo **user token** tại: **Account → My Account → Security**

---

### Cài đặt SonarScanner

* Tải tại: [https://docs.sonarsource.com/sonarqube/latest/analysis/scan/sonarscanner/](https://docs.sonarsource.com/sonarqube/latest/analysis/scan/sonarscanner/)
* Giải nén
* Nhấn `Win + S`, tìm: `Edit the system environment variables`
* Bấm `Environment Variables`
* Ở User `variables for Vu`, bấn `New`
* Thêm Name: `SONAR_SCANNER_HOME`, Value: `D:\SonarScanner`
* Tìm biến `PATH` trong `User Variables`
* Thêm đường dẫn vào `PATH`: `%SONAR_SCANNER_HOME%\bin`

Kiểm tra

Mở `Command Prompt (cmd)` và gõ

```bash
sonar-scanner -v
```

---

## 2. Tạo cấu hình `sonar-project.properties`

```properties
sonar.projectKey=elec
sonar.projectName=elec
sonar.projectVersion=1.0
sonar.sources=.
sonar.java.binaries=.
sonar.host.url=http://localhost:9000
sonar.login=<token>
```

---

## 3. Các vấn đề được phát hiện ban đầu bởi SonarQube

### Tổng quan
- 🔎 **Tổng số vấn phát hiện**: `26`
- ⏱️ **Tổng thời gian sửa ước lượng**: ` 157 phút (~2 giờ 37 phút)`
- ⚠️ **Mức độ nghiêm trọng cao nhất**: `CRITICAL`
### 📸 Lỗi chi tiết từng file
![Chi tiết lỗi](./SonarQube_Images/Ảnh%20chụp%20màn%20hình%202025-07-13%20153903.png)
![Chi tiết lỗi](./SonarQube_Images/Ảnh%20chụp%20màn%20hình%202025-07-13%20153952.png)
![Chi tiết lỗi](./SonarQube_Images/Ảnh%20chụp%20màn%20hình%202025-07-13%20154017.png)
![Chi tiết lỗi](./SonarQube_Images/Ảnh%20chụp%20màn%20hình%202025-07-13%20154043.png)
![Chi tiết lỗi](./SonarQube_Images/Ảnh%20chụp%20màn%20hình%202025-07-13%20154118.png)
![Chi tiết lỗi](./SonarQube_Images/Ảnh%20chụp%20màn%20hình%202025-07-13%20154155.png)
![Chi tiết lỗi](./SonarQube_Images/Ảnh%20chụp%20màn%20hình%202025-07-13%20154212.png)
---

### 🐛 Danh sách chi tiết các vấn đề

### 🐛 Danh sách chi tiết các vấn đề (không bao gồm BLOCKER)

| STT | File                                           | Dòng | Rule            | Mức độ          | Loại         | Mô tả                                                              | Ước lượng sửa |
|-----|------------------------------------------------|------|------------------|------------------|--------------|--------------------------------------------------------------------|----------------|
| 1   | `Role.java`                                    | 4    | `java:S115`     | 🟧 **CRITICAL**  | Code Smell   | Đổi tên hằng số để đúng định dạng `^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$`  | `2 phút`       |
| 2   | `Role.java`                                    | 5    | `java:S115`     | 🟧 **CRITICAL**  | Code Smell   | Đổi tên hằng số để đúng định dạng `^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$`  | `2 phút`       |
| 3   | `AppException.java`                            | 9    | `java:S1165`    | 🟩 **MINOR**     | Code Smell   | Nên khai báo trường `errorCode` là `final`.                        | `15 phút`      |
| 4   | `BrandSpecification.java`                      | 6    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 5   | `CategorySpecification.java`                   | 6    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 6   | `EntityFileSpecification.java`                 | 6    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 7   | `FileSpecification.java`                       | 6    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 8   | `OptionSpecification.java`                     | 6    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 9   | `OptionValueSpecification.java`                | 7    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 10  | `OrderProductSpecification.java`               | 7    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 11  | `OrderProductVariationSpecification.java`      | 6    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 12  | `OrderProductVariationValueSpecification.java` | 6    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 13  | `OrderSpecification.java`                      | 8    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 14  | `ProductSpecification.java`                    | 8    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 15  | `ProductSpecification.java`                    | 35   | `java:S1192`    | 🟧 **CRITICAL**  | Code Smell   | Trùng lặp literal `"price"` → nên đưa thành hằng số.               | `8 phút`       |
| 16  | `ProductVariantSpecification.java`             | 8    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 17  | `ProductVariantSpecification.java`             | 35   | `java:S1192`    | 🟧 **CRITICAL**  | Code Smell   | Trùng lặp literal `"price"` → nên đưa thành hằng số.               | `8 phút`       |
| 18  | `UserSpecification.java`                       | 7    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 19  | `VariationSpecification.java`                  | 6    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 20  | `VariationValueSpecification.java`             | 6    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 21  | `JwtTokenUtil.java`                            | 53   | `java:S112`     | 🟩 **MAJOR**     | Code Smell   | Nên dùng custom exception thay vì Exception chung chung.           | `20 phút`      |
| 22  | `PageUtil.java`                                | 8    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 23  | `SpecUtil.java`                                | 5    | `java:S1118`    | 🟩 **MAJOR**     | Code Smell   | Thêm constructor `private` để ẩn constructor public mặc định.      | `5 phút`       |
| 24  | `ElecApplicationTests.java`                    | 10   | `java:S1186`    | 🟧 **CRITICAL**  | Code Smell   | Method rỗng → thêm comment hoặc ném UnsupportedOperationException. | `5 phút`       |
| 25  | `JwtDecoder.java`                              | 19   | `java:S2176`    | 🟧 **CRITICAL**  | Code Smell   | Đổi tên class để tránh nhầm lẫn.                                   | `5 phút`       |
| 26  | `SecurityConfig.java`                          | 19   | `java:S116`     | 🟩 **MINOR**     | Code Smell   | Đổi tên field `PUBLIC_ENDPOINTS` để đúng chuẩn camelCase.          | `2 phút`       |

---

### 📌 Ghi chú

- 🟥 **BLOCKER**: Mức nghiêm trọng nhất, ảnh hưởng trực tiếp đến **bảo mật** và **tính tin cậy** của hệ thống.
- 🟧 **CRITICAL**: Lỗi nghiêm trọng cần xử lý sớm.
- 🟩 **MAJOR** / **MINOR**: Các lỗi về cấu trúc mã nguồn hoặc khuyến nghị cải thiện, không ảnh hưởng đến chức năng hệ thống.


## 4. Mã nguồn sau khi cải thiện

Dưới đây là một số đoạn mã trước và sau khi được cải thiện theo các cảnh báo SonarQube nhằm nâng cao chất lượng và bảo mật của hệ thống.

---

### 🔁 **1 và 2. Đổi tên hằng số enum (`java:S115`)**
#### Trước khi cải thiện (`Role.java`)
```java
public enum Role {
  Admin,
  User;
}
```
#### Sau khi cải thiện (`Role.java`)
```java
public enum Role {
    ADMIN,
    USER;
}
```
### 🔁 **3. Khai báo trường `final` cho immutable field (`java:S1165`)**
#### Trước khi cải thiện (`AppException.java`)
```java
@Getter
@Setter
public class AppException extends RuntimeException {
  private ErrorCode errorCode;

  public AppException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
```
#### Sau khi cải thiện (`AppException.java`)
```java
@Getter
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
```
### 🔁 **4. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**
#### Trước khi cải thiện (`BrandSpecification.java`)
```java
public class BrandSpecification {
  public static Specification<Brand> nameContains(String keyword) {
    return (root, query, cb) -> {
      if (keyword == null || keyword.isBlank()) return null;
      return cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
    };
  }
  public static Specification<Brand> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }
}
```
#### Sau khi cải thiện (`BrandSpecification.java`)
```java
public class BrandSpecification {
    private BrandSpecification() {}
    public static Specification<Brand> nameContains(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isBlank()) return null;
            return cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
        };
    }
    public static Specification<Brand> isActive(Boolean isActive) {
        return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
    }
}

```
### 🔁 **5. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`CategorySpecification.java`)
```java
public class CategorySpecification {
  public static Specification<Category> nameContains(String keyword) {
    return (root, query, cb) ->
        (keyword == null || keyword.isBlank())
            ? null
            : cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
  }
  public static Specification<Category> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }
  public static Specification<Category> hasParentId(Integer parentId) {
    return (root, query, cb) ->
        parentId == null ? null : cb.equal(root.get("parent").get("id"), parentId);
  }
}
```
#### Sau khi cải thiện (`BrandSpecification.java`)
```java
public class CategorySpecification {
  private CategorySpecification() {}
  public static Specification<Category> nameContains(String keyword) {
    return (root, query, cb) ->
        (keyword == null || keyword.isBlank())
            ? null
            : cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
  }
  public static Specification<Category> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }
  public static Specification<Category> hasParentId(Integer parentId) {
    return (root, query, cb) ->
        parentId == null ? null : cb.equal(root.get("parent").get("id"), parentId);
  }
}
```

### 🔁 **6. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`EntityFileSpecification.java`)
```java
public class EntityFileSpecification {

  public static Specification<EntityFile> hasEntityId(Integer entityId) {
    return (root, query, cb) -> entityId == null ? null : cb.equal(root.get("entityId"), entityId);
  }

  public static Specification<EntityFile> hasEntityType(String entityType) {
    return (root, query, cb) ->
        (entityType == null || entityType.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("entityType")), entityType.toLowerCase());
  }

  public static Specification<EntityFile> hasZone(String zone) {
    return (root, query, cb) ->
        (zone == null || zone.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("zone")), zone.toLowerCase());
  }
}
```
#### Sau khi cải thiện (`EntityFileSpecification.java`)
```java
public class EntityFileSpecification {
  private EntityFileSpecification() {}
  public static Specification<EntityFile> hasEntityId(Integer entityId) {
    return (root, query, cb) -> entityId == null ? null : cb.equal(root.get("entityId"), entityId);
  }
  public static Specification<EntityFile> hasEntityType(String entityType) {
    return (root, query, cb) ->
        (entityType == null || entityType.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("entityType")), entityType.toLowerCase());
  }
  public static Specification<EntityFile> hasZone(String zone) {
    return (root, query, cb) ->
        (zone == null || zone.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("zone")), zone.toLowerCase());
  }
}
```
### 🔁 **7. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`FileSpecification.java`)
```java
public class FileSpecification {
  public static Specification<File> hasUserId(Integer userId) {
    return (root, query, cb) -> userId == null ? null : cb.equal(root.get("userId"), userId);
  }
  public static Specification<File> hasFilename(String filename) {
    return (root, query, cb) ->
        (filename == null || filename.isBlank())
            ? null
            : cb.like(cb.lower(root.get("filename")), "%" + filename.toLowerCase() + "%");
  }
  public static Specification<File> hasMime(String mime) {
    return (root, query, cb) ->
        (mime == null || mime.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("mime")), mime.toLowerCase());
  }
  public static Specification<File> hasExtension(String extension) {
    return (root, query, cb) ->
        (extension == null || extension.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("extension")), extension.toLowerCase());
  }
}
```
#### Sau khi cải thiện (`FileSpecification.java`)
```java
public class FileSpecification {
  private FileSpecification() {}
  public static Specification<File> hasUserId(Integer userId) {
    return (root, query, cb) -> userId == null ? null : cb.equal(root.get("userId"), userId);
  }
  public static Specification<File> hasFilename(String filename) {
    return (root, query, cb) ->
        (filename == null || filename.isBlank())
            ? null
            : cb.like(cb.lower(root.get("filename")), "%" + filename.toLowerCase() + "%");
  }
  public static Specification<File> hasMime(String mime) {
    return (root, query, cb) ->
        (mime == null || mime.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("mime")), mime.toLowerCase());
  }
  public static Specification<File> hasExtension(String extension) {
    return (root, query, cb) ->
        (extension == null || extension.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("extension")), extension.toLowerCase());
  }
}
```
### 🔁 **8. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`OptionSpecification.java`)
```java
public class OptionSpecification {
  public static Specification<Option> hasType(String type) {
    return (root, query, cb) ->
        type == null || type.isBlank()
            ? null
            : cb.equal(cb.lower(root.get("type")), type.toLowerCase());
  }
  public static Specification<Option> isRequired(Boolean required) {
    return (root, query, cb) ->
        required == null ? null : cb.equal(root.get("isRequired"), required);
  }
  public static Specification<Option> isGlobal(Boolean global) {
    return (root, query, cb) -> global == null ? null : cb.equal(root.get("isGlobal"), global);
  }
}
```
#### Sau khi cải thiện (`OptionSpecification.java`)
```java
public class OptionSpecification {
  private OptionSpecification() {}
  public static Specification<Option> hasType(String type) {
    return (root, query, cb) ->
        type == null || type.isBlank()
            ? null
            : cb.equal(cb.lower(root.get("type")), type.toLowerCase());
  }
  public static Specification<Option> isRequired(Boolean required) {
    return (root, query, cb) ->
        required == null ? null : cb.equal(root.get("isRequired"), required);
  }
  public static Specification<Option> isGlobal(Boolean global) {
    return (root, query, cb) -> global == null ? null : cb.equal(root.get("isGlobal"), global);
  }
}
```
### 🔁 **9. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`OptionValueSpecification.java`)
```java
public class OptionValueSpecification {
  public static Specification<OptionValue> hasOptionId(Integer optionId) {
    return (root, query, cb) ->
        optionId == null ? null : cb.equal(root.get("option").get("id"), optionId);
  }
  public static Specification<OptionValue> hasPriceType(String priceType) {
    return (root, query, cb) ->
        priceType == null || priceType.isBlank()
            ? null
            : cb.equal(cb.lower(root.get("priceType")), priceType.toLowerCase());
  }
  public static Specification<OptionValue> hasMinPrice(BigDecimal minPrice) {
    return (root, query, cb) ->
        minPrice == null ? null : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
  }
  public static Specification<OptionValue> hasMaxPrice(BigDecimal maxPrice) {
    return (root, query, cb) ->
        maxPrice == null ? null : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
  }
}
```
#### Sau khi cải thiện (`OptionValueSpecification.java`)
```java
public class OptionValueSpecification {
    private OptionValueSpecification() {}
    public static Specification<OptionValue> hasOptionId(Integer optionId) {
        return (root, query, cb) ->
                optionId == null ? null : cb.equal(root.get("option").get("id"), optionId);
    }
    public static Specification<OptionValue> hasPriceType(String priceType) {
        return (root, query, cb) ->
                priceType == null || priceType.isBlank()
                        ? null
                        : cb.equal(cb.lower(root.get("priceType")), priceType.toLowerCase());
    }
    public static Specification<OptionValue> hasMinPrice(BigDecimal minPrice) {
        return (root, query, cb) ->
                minPrice == null ? null : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }
    public static Specification<OptionValue> hasMaxPrice(BigDecimal maxPrice) {
        return (root, query, cb) ->
                maxPrice == null ? null : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
```
### 🔁 **10. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`OrderProductSpecification.java`)
```java
public class OrderProductSpecification {
  public static Specification<OrderProduct> hasOrderId(Integer orderId) {
    return (root, query, cb) ->
        orderId == null ? null : cb.equal(root.get("order").get("id"), orderId);
  }
  public static Specification<OrderProduct> hasProductId(Integer productId) {
    return (root, query, cb) ->
        productId == null ? null : cb.equal(root.get("product").get("id"), productId);
  }
  public static Specification<OrderProduct> hasProductVariantId(Integer variantId) {
    return (root, query, cb) ->
        variantId == null ? null : cb.equal(root.get("productVariant").get("id"), variantId);
  }
  public static Specification<OrderProduct> hasMinLineTotal(BigDecimal min) {
    return (root, query, cb) ->
        min == null ? null : cb.greaterThanOrEqualTo(root.get("lineTotal"), min);
  }
  public static Specification<OrderProduct> hasMaxLineTotal(BigDecimal max) {
    return (root, query, cb) ->
        max == null ? null : cb.lessThanOrEqualTo(root.get("lineTotal"), max);
  }
}
```
#### Sau khi cải thiện (`OrderProductSpecification.java`)
```java
public class OrderProductSpecification {
    private OrderProductSpecification() {}
    public static Specification<OrderProduct> hasOrderId(Integer orderId) {
        return (root, query, cb) ->
                orderId == null ? null : cb.equal(root.get("order").get("id"), orderId);
    }
    public static Specification<OrderProduct> hasProductId(Integer productId) {
        return (root, query, cb) ->
                productId == null ? null : cb.equal(root.get("product").get("id"), productId);
    }
    public static Specification<OrderProduct> hasProductVariantId(Integer variantId) {
        return (root, query, cb) ->
                variantId == null ? null : cb.equal(root.get("productVariant").get("id"), variantId);
    }
    public static Specification<OrderProduct> hasMinLineTotal(BigDecimal min) {
        return (root, query, cb) ->
                min == null ? null : cb.greaterThanOrEqualTo(root.get("lineTotal"), min);
    }
    public static Specification<OrderProduct> hasMaxLineTotal(BigDecimal max) {
        return (root, query, cb) ->
                max == null ? null : cb.lessThanOrEqualTo(root.get("lineTotal"), max);
    }
}
```
### 🔁 **11. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`OrderProductVariationSpecification.java`)
```java
public class OrderProductVariationSpecification {
  public static Specification<OrderProductVariation> hasOrderProductId(Integer id) {
    return (root, query, cb) ->
        id == null ? null : cb.equal(root.get("orderProduct").get("id"), id);
  }
  public static Specification<OrderProductVariation> hasVariationId(Integer id) {
    return (root, query, cb) -> id == null ? null : cb.equal(root.get("variation").get("id"), id);
  }
  public static Specification<OrderProductVariation> hasType(String type) {
    return (root, query, cb) ->
        (type == null || type.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("type")), type.toLowerCase());
  }
  public static Specification<OrderProductVariation> hasValue(String value) {
    return (root, query, cb) ->
        (value == null || value.isBlank())
            ? null
            : cb.like(cb.lower(root.get("value")), "%" + value.toLowerCase() + "%");
  }
}
```
#### Sau khi cải thiện (`OrderProductVariationSpecification.java`)
```java
public class OrderProductVariationSpecification {
  private OrderProductVariationSpecification() {}
  public static Specification<OrderProductVariation> hasOrderProductId(Integer id) {
    return (root, query, cb) ->
        id == null ? null : cb.equal(root.get("orderProduct").get("id"), id);
  }
  public static Specification<OrderProductVariation> hasVariationId(Integer id) {
    return (root, query, cb) -> id == null ? null : cb.equal(root.get("variation").get("id"), id);
  }
  public static Specification<OrderProductVariation> hasType(String type) {
    return (root, query, cb) ->
        (type == null || type.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("type")), type.toLowerCase());
  }
  public static Specification<OrderProductVariation> hasValue(String value) {
    return (root, query, cb) ->
        (value == null || value.isBlank())
            ? null
            : cb.like(cb.lower(root.get("value")), "%" + value.toLowerCase() + "%");
  }
}
```
### 🔁 **12. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`OrderProductVariationValueSpecification.java`)
```java
public class OrderProductVariationValueSpecification {
  public static Specification<OrderProductVariationValue> hasOrderProductVariationId(Integer id) {
    return (root, query, cb) ->
        id == null ? null : cb.equal(root.get("orderProductVariation").get("id"), id);
  }
  public static Specification<OrderProductVariationValue> hasVariationValueId(Integer id) {
    return (root, query, cb) ->
        id == null ? null : cb.equal(root.get("variationValue").get("id"), id);
  }
}
```
#### Sau khi cải thiện (`OrderProductVariationValueSpecification.java`)
```java
public class OrderProductVariationValueSpecification {
    private OrderProductVariationValueSpecification() {}
    public static Specification<OrderProductVariationValue> hasOrderProductVariationId(Integer id) {
        return (root, query, cb) ->
                id == null ? null : cb.equal(root.get("orderProductVariation").get("id"), id);
    }
    public static Specification<OrderProductVariationValue> hasVariationValueId(Integer id) {
        return (root, query, cb) ->
                id == null ? null : cb.equal(root.get("variationValue").get("id"), id);
    }
}
```
### 🔁 **13. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`OrderSpecification.java`)
```java
public class OrderSpecification {
  public static Specification<Order> hasCustomerEmail(String email) {
    return (root, query, cb) ->
        email == null || email.isBlank()
            ? null
            : cb.like(cb.lower(root.get("customerEmail")), "%" + email.toLowerCase() + "%");
  }
  public static Specification<Order> hasCustomerPhone(String phone) {
    return (root, query, cb) ->
        phone == null || phone.isBlank()
            ? null
            : cb.like(cb.lower(root.get("customerPhone")), "%" + phone.toLowerCase() + "%");
  }
  public static Specification<Order> hasStatus(String status) {
    return (root, query, cb) ->
        status == null || status.isBlank() ? null : cb.equal(root.get("status"), status);
  }
  public static Specification<Order> createdAfter(LocalDateTime from) {
    return (root, query, cb) ->
        from == null ? null : cb.greaterThanOrEqualTo(root.get("createdAt"), from);
  }
  public static Specification<Order> createdBefore(LocalDateTime to) {
    return (root, query, cb) -> to == null ? null : cb.lessThanOrEqualTo(root.get("createdAt"), to);
  }
  public static Specification<Order> totalGreaterThan(BigDecimal min) {
    return (root, query, cb) ->
        min == null ? null : cb.greaterThanOrEqualTo(root.get("total"), min);
  }
  public static Specification<Order> totalLessThan(BigDecimal max) {
    return (root, query, cb) -> max == null ? null : cb.lessThanOrEqualTo(root.get("total"), max);
  }
}
```
#### Sau khi cải thiện (`OrderSpecification.java`)
```java
public class OrderSpecification {
  private OrderSpecification() {}
  public static Specification<Order> hasCustomerEmail(String email) {
    return (root, query, cb) ->
        email == null || email.isBlank()
            ? null
            : cb.like(cb.lower(root.get("customerEmail")), "%" + email.toLowerCase() + "%");
  }
  public static Specification<Order> hasCustomerPhone(String phone) {
    return (root, query, cb) ->
        phone == null || phone.isBlank()
            ? null
            : cb.like(cb.lower(root.get("customerPhone")), "%" + phone.toLowerCase() + "%");
  }
  public static Specification<Order> hasStatus(String status) {
    return (root, query, cb) ->
        status == null || status.isBlank() ? null : cb.equal(root.get("status"), status);
  }
  public static Specification<Order> createdAfter(LocalDateTime from) {
    return (root, query, cb) ->
        from == null ? null : cb.greaterThanOrEqualTo(root.get("createdAt"), from);
  }
  public static Specification<Order> createdBefore(LocalDateTime to) {
    return (root, query, cb) -> to == null ? null : cb.lessThanOrEqualTo(root.get("createdAt"), to);
  }
  public static Specification<Order> totalGreaterThan(BigDecimal min) {
    return (root, query, cb) ->
        min == null ? null : cb.greaterThanOrEqualTo(root.get("total"), min);
  }
  public static Specification<Order> totalLessThan(BigDecimal max) {
    return (root, query, cb) -> max == null ? null : cb.lessThanOrEqualTo(root.get("total"), max);
  }
}
```
### 🔁 **14. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`ProductSpecification.java`)
```java
public class ProductSpecification {
  public static Specification<Product> keywordContains(String keyword) {
    return (root, query, cb) ->
        keyword == null || keyword.isBlank()
            ? null
            : cb.or(
                cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%"));
  }
  public static Specification<Product> hasBrand(Integer brandId) {
    return (root, query, cb) ->
        brandId == null ? null : cb.equal(root.get("brand").get("id"), brandId);
  }
  public static Specification<Product> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }
  public static Specification<Product> inStock(Boolean inStock) {
    return (root, query, cb) -> inStock == null ? null : cb.equal(root.get("inStock"), inStock);
  }
  public static Specification<Product> priceBetween(BigDecimal min, BigDecimal max) {
    return (root, query, cb) -> {
      if (min == null && max == null) return null;
      if (min != null && max != null) return cb.between(root.get("price"), min, max);
      if (min != null) return cb.greaterThanOrEqualTo(root.get("price"), min);
      return cb.lessThanOrEqualTo(root.get("price"), max);
    };
  }
  public static Specification<Product> newDateBetween(LocalDateTime from, LocalDateTime to) {
    return (root, query, cb) -> {
      if (from == null && to == null) return null;
      if (from != null && to != null) return cb.between(root.get("newFrom"), from, to);
      if (from != null) return cb.greaterThanOrEqualTo(root.get("newFrom"), from);
      return cb.lessThanOrEqualTo(root.get("newTo"), to);
    };
  }
}
```
#### Sau khi cải thiện (`ProductSpecification.java`)
```java
public class ProductSpecification {
  private ProductSpecification() {}
  public static Specification<Product> keywordContains(String keyword) {
    return (root, query, cb) ->
        keyword == null || keyword.isBlank()
            ? null
            : cb.or(
                cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%"));
  }
  public static Specification<Product> hasBrand(Integer brandId) {
    return (root, query, cb) ->
        brandId == null ? null : cb.equal(root.get("brand").get("id"), brandId);
  }
  public static Specification<Product> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }
  public static Specification<Product> inStock(Boolean inStock) {
    return (root, query, cb) -> inStock == null ? null : cb.equal(root.get("inStock"), inStock);
  }
  public static Specification<Product> priceBetween(BigDecimal min, BigDecimal max) {
    return (root, query, cb) -> {
      if (min == null && max == null) return null;
      if (min != null && max != null) return cb.between(root.get("price"), min, max);
      if (min != null) return cb.greaterThanOrEqualTo(root.get("price"), min);
      return cb.lessThanOrEqualTo(root.get("price"), max);
    };
  }
  public static Specification<Product> newDateBetween(LocalDateTime from, LocalDateTime to) {
    return (root, query, cb) -> {
      if (from == null && to == null) return null;
      if (from != null && to != null) return cb.between(root.get("newFrom"), from, to);
      if (from != null) return cb.greaterThanOrEqualTo(root.get("newFrom"), from);
      return cb.lessThanOrEqualTo(root.get("newTo"), to);
    };
  }
}
```
### 🔁 **15. Tránh lặp lại literal `"price"` (`java:S1192`)**

#### Trước khi cải thiện (`ProductSpecification.java`)
```java
public class ProductSpecification {
  public static Specification<Product> priceBetween(BigDecimal min, BigDecimal max) {
    return (root, query, cb) -> {
      if (min == null && max == null) return null;
      if (min != null && max != null) return cb.between(root.get("price"), min, max);
      if (min != null) return cb.greaterThanOrEqualTo(root.get("price"), min);
      return cb.lessThanOrEqualTo(root.get("price"), max);
    };
  }
}
```
#### Sau khi cải thiện (`ProductSpecification.java`)
```java
public class ProductSpecification {

    private ProductSpecification() {}

    private static final String FIELD_PRICE = "price";

    public static Specification<Product> priceBetween(BigDecimal min, BigDecimal max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min != null && max != null) return cb.between(root.get(FIELD_PRICE), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get(FIELD_PRICE), min);
            return cb.lessThanOrEqualTo(root.get(FIELD_PRICE), max);
        };
    }
}
```
### 🔁 **16. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`ProductVariantSpecification.java`)
```java
public class ProductVariantSpecification {
  public static Specification<ProductVariant> hasProductId(Integer productId) {
    return (root, query, cb) ->
        productId == null ? null : cb.equal(root.get("product").get("id"), productId);
  }
  public static Specification<ProductVariant> keywordContains(String keyword) {
    return (root, query, cb) ->
        keyword == null || keyword.isBlank()
            ? null
            : cb.or(
                cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("sku")), "%" + keyword.toLowerCase() + "%"));
  }
  public static Specification<ProductVariant> isInStock(Boolean inStock) {
    return (root, query, cb) -> inStock == null ? null : cb.equal(root.get("inStock"), inStock);
  }
  public static Specification<ProductVariant> isActive(Boolean isActive) {
    return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
  }
  public static Specification<ProductVariant> priceBetween(BigDecimal min, BigDecimal max) {
    return (root, query, cb) -> {
      if (min == null && max == null) return null;
      if (min != null && max != null) return cb.between(root.get("price"), min, max);
      if (min != null) return cb.greaterThanOrEqualTo(root.get("price"), min);
      return cb.lessThanOrEqualTo(root.get("price"), max);
    };
  }
  public static Specification<ProductVariant> deletedBefore(LocalDateTime deletedAt) {
    return (root, query, cb) ->
        deletedAt == null ? null : cb.lessThanOrEqualTo(root.get("deletedAt"), deletedAt);
  }
}
```
#### Sau khi cải thiện (`ProductVariantSpecification.java`)
```java
public class ProductVariantSpecification {
    private ProductVariantSpecification() {}
    public static Specification<ProductVariant> hasProductId(Integer productId) {
        return (root, query, cb) ->
                productId == null ? null : cb.equal(root.get("product").get("id"), productId);
    }
    public static Specification<ProductVariant> keywordContains(String keyword) {
        return (root, query, cb) ->
                keyword == null || keyword.isBlank()
                        ? null
                        : cb.or(
                        cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"),
                        cb.like(cb.lower(root.get("sku")), "%" + keyword.toLowerCase() + "%"));
    }
    public static Specification<ProductVariant> isInStock(Boolean inStock) {
        return (root, query, cb) -> inStock == null ? null : cb.equal(root.get("inStock"), inStock);
    }
    public static Specification<ProductVariant> isActive(Boolean isActive) {
        return (root, query, cb) -> isActive == null ? null : cb.equal(root.get("isActive"), isActive);
    }
    public static Specification<ProductVariant> priceBetween(BigDecimal min, BigDecimal max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min != null && max != null) return cb.between(root.get("price"), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get("price"), min);
            return cb.lessThanOrEqualTo(root.get("price"), max);
        };
    }
    public static Specification<ProductVariant> deletedBefore(LocalDateTime deletedAt) {
        return (root, query, cb) ->
                deletedAt == null ? null : cb.lessThanOrEqualTo(root.get("deletedAt"), deletedAt);
    }
}
```
### 🔁 **17. Tránh lặp lại literal `"price"` (`java:S1192`)**
#### Trước khi cải thiện (`ProductVariantSpecification.java`)
```java
public static Specification<ProductVariant> priceBetween(BigDecimal min, BigDecimal max) {
  return (root, query, cb) -> {
    if (min == null && max == null) return null;
    if (min != null && max != null) return cb.between(root.get("price"), min, max);
    if (min != null) return cb.greaterThanOrEqualTo(root.get("price"), min);
    return cb.lessThanOrEqualTo(root.get("price"), max);
  };
}
```
#### Sau khi cải thiện (`ProductVariantSpecification.java`)
```java
public class ProductVariantSpecification {

    private ProductVariantSpecification() {}

    private static final String PRICE_FIELD = "price";

    public static Specification<ProductVariant> priceBetween(BigDecimal min, BigDecimal max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min != null && max != null) return cb.between(root.get(PRICE_FIELD), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get(PRICE_FIELD), min);
            return cb.lessThanOrEqualTo(root.get(PRICE_FIELD), max);
        };
    }
}

```
### 🔁 **18. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`UserSpecification.java`)
```java
public class UserSpecification {
  public static Specification<User> keywordContains(String keyword) {
    return (root, query, cb) ->
        keyword == null || keyword.isBlank()
            ? null
            : cb.or(
                cb.like(cb.lower(root.get("firstName")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("lastName")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("email")), "%" + keyword.toLowerCase() + "%"),
                cb.like(cb.lower(root.get("phone")), "%" + keyword.toLowerCase() + "%"));
  }
  public static Specification<User> hasRole(Role role) {
    return (root, query, cb) -> role == null ? null : cb.equal(root.get("role"), role);
  }
  public static Specification<User> isEnabled(Boolean enabled) {
    return (root, query, cb) -> enabled == null ? null : cb.equal(root.get("enabled"), enabled);
  }
}
```
#### Sau khi cải thiện (`UserSpecification.java`)
```java
public class UserSpecification {
    private UserSpecification() {}
    public static Specification<User> keywordContains(String keyword) {
        return (root, query, cb) ->
                keyword == null || keyword.isBlank()
                        ? null
                        : cb.or(
                        cb.like(cb.lower(root.get("firstName")), "%" + keyword.toLowerCase() + "%"),
                        cb.like(cb.lower(root.get("lastName")), "%" + keyword.toLowerCase() + "%"),
                        cb.like(cb.lower(root.get("email")), "%" + keyword.toLowerCase() + "%"),
                        cb.like(cb.lower(root.get("phone")), "%" + keyword.toLowerCase() + "%"));
    }
    public static Specification<User> hasRole(Role role) {
        return (root, query, cb) -> role == null ? null : cb.equal(root.get("role"), role);
    }
    public static Specification<User> isEnabled(Boolean enabled) {
        return (root, query, cb) -> enabled == null ? null : cb.equal(root.get("enabled"), enabled);
    }
}
```
### 🔁 **19. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`VariationSpecification.java`)
```java
public class VariationSpecification {
  public static Specification<Variation> hasName(String name) {
    return (root, query, cb) ->
        name == null || name.isBlank()
            ? null
            : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }
  public static Specification<Variation> hasType(String type) {
    return (root, query, cb) ->
        type == null || type.isBlank()
            ? null
            : cb.equal(cb.lower(root.get("type")), type.toLowerCase());
  }
  public static Specification<Variation> isGlobal(Boolean isGlobal) {
    return (root, query, cb) -> isGlobal == null ? null : cb.equal(root.get("isGlobal"), isGlobal);
  }
}
```
#### Sau khi cải thiện (`VariationSpecification.java`)
```java
public class VariationSpecification {
    private VariationSpecification() {}
    public static Specification<Variation> hasName(String name) {
        return (root, query, cb) ->
                name == null || name.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
    public static Specification<Variation> hasType(String type) {
        return (root, query, cb) ->
                type == null || type.isBlank()
                        ? null
                        : cb.equal(cb.lower(root.get("type")), type.toLowerCase());
    }
    public static Specification<Variation> isGlobal(Boolean isGlobal) {
        return (root, query, cb) -> isGlobal == null ? null : cb.equal(root.get("isGlobal"), isGlobal);
    }
}
```
### 🔁 **20. Ẩn constructor mặc định trong lớp utility (`java:S1118`)**

#### Trước khi cải thiện (`VariationValueSpecification.java`)
```java
public class VariationValueSpecification {
    public static Specification<VariationValue> hasLabel(String label) {
        return (root, query, cb) ->
                label == null || label.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("label")), "%" + label.toLowerCase() + "%");
    }
    public static Specification<VariationValue> hasValue(String value) {
        return (root, query, cb) ->
                value == null || value.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("value")), "%" + value.toLowerCase() + "%");
    }
    public static Specification<VariationValue> hasVariationId(Integer variationId) {
        return (root, query, cb) ->
                variationId == null ? null : cb.equal(root.get("variation").get("id"), variationId);
    }
}
```
#### Sau khi cải thiện (`VariationValueSpecification.java`)
```java
public class VariationValueSpecification {
    private VariationValueSpecification() {}
    public static Specification<VariationValue> hasLabel(String label) {
        return (root, query, cb) ->
                label == null || label.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("label")), "%" + label.toLowerCase() + "%");
    }
    public static Specification<VariationValue> hasValue(String value) {
        return (root, query, cb) ->
                value == null || value.isBlank()
                        ? null
                        : cb.like(cb.lower(root.get("value")), "%" + value.toLowerCase() + "%");
    }
    public static Specification<VariationValue> hasVariationId(Integer variationId) {
        return (root, query, cb) ->
                variationId == null ? null : cb.equal(root.get("variation").get("id"), variationId);
    }
}
```
### 🔁 **21. Tránh sử dụng `Exception` hoặc `RuntimeException` chung chung (`java:S112`)**

#### Trước khi cải thiện (`JwtTokenUtil.java`)
```java
public String generateToken(User user, int expirationMinutes, String secret, String tokenType) {
  try {
    // ... xử lý sinh token
    SignedJWT signedJWT = new SignedJWT(header, claimsSet);
    signedJWT.sign(new MACSigner(secret.getBytes()));
    return signedJWT.serialize();
  } catch (JOSEException e) {
    log.error("Error generating token", e);
    throw new RuntimeException(e); //RuntimeException không rõ ràng
  }
}
```
#### Sau khi cải thiện (`JwtTokenUtil.java`)
```java
//org.soft.elec.exception.ErrorCode
TOKEN_GENERATION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while generating JWT token.");

//org.soft.elec.utils.JwtTokenUtil
public String generateToken(User user, int expirationMinutes, String secret, String tokenType) {
    try {
        // ... xử lý sinh token
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        signedJWT.sign(new MACSigner(secret.getBytes()));
        return signedJWT.serialize();
    } catch (JOSEException e) {
        log.error("Error generating token", e);
        throw new AppException(ErrorCode.TOKEN_GENERATION_FAILED); // Dùng custom exception
    }
}
```
### 🔁 **22. Ẩn constructor mặc định trong lớp tiện ích (`java:S1118`)**

#### Trước khi cải thiện (`PageUtil.java`)
```java
public class PageUtil {
  public static Pageable getPageable(BaseSearchRequest request) {
    return PageRequest.of(
        request.getPage(), request.getSize(), Sort.by(request.getDirection(), request.getSortBy()));
  }
}
```

#### Sau khi cải thiện (`PageUtil.java`)
```java
public class PageUtil {
  private PageUtil() {}
  public static Pageable getPageable(BaseSearchRequest request) {
    return PageRequest.of(
        request.getPage(), request.getSize(), Sort.by(request.getDirection(), request.getSortBy()));
  }
}
```
### 🔁 **23. Ẩn constructor mặc định trong lớp tiện ích (`java:S1118`)**

#### rước khi cải thiện (`SpecUtil.java`)
```java
public class SpecUtil {
  public static <T> Specification<T> add(Specification<T> base, Specification<T> addition) {
    if (base == null) return addition;
    if (addition == null) return base;
    return base.and(addition);
  }
}
```

#### Sau khi cải thiện (`SpecUtil.java`)
```java
public class SpecUtil {
  private SpecUtil() {}
  public static <T> Specification<T> add(Specification<T> base, Specification<T> addition) {
    if (base == null) return addition;
    if (addition == null) return base;
    return base.and(addition);
  }
}
```

### 🔁 **24. Xử lý method rỗng theo chuẩn (`java:S1186`)**

#### Trước khi cải thiện (`ElecApplicationTests.java`)
```java
@SpringBootTest
class ElecApplicationTests {

  @Test
  void contextLoads() {}
}
```

#### Sau khi cải thiện (`ElecApplicationTests.java`)
```java
@SpringBootTest
class ElecApplicationTests {

  @Test
  void contextLoads() {
    // Test context loading
  }
}
```
### 🔁 **25. Đổi tên class để tránh hiểu nhầm (`java:S2176`)**

#### Trước khi cải thiện (`JwtDecoder.java`)
```java
@Component
public class JwtDecoder implements org.springframework.security.oauth2.jwt.JwtDecoder {
  @Value("${security.jwt.access-secret}")
  private String accessSecretKey;
  private final AuthService authService;
  public JwtDecoder(AuthService authService) {
    this.authService = authService;
  }
  @Override
  public Jwt decode(String token) {
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);
      String tokenType = (String) signedJWT.getJWTClaimsSet().getClaim("token_type");
      if (tokenType == null || !tokenType.equalsIgnoreCase("access")) {
        throw new AppException(ErrorCode.UNAUTHENTICATED);
      }
      var response = authService.introspect(IntrospectRequest.builder().accessToken(token).build());
      if (!response.isValid()) {
        throw new AppException(ErrorCode.UNAUTHENTICATED);
      }
      SecretKeySpec secretKeySpec = new SecretKeySpec(accessSecretKey.getBytes(), "HS256");
      NimbusJwtDecoder decoder =
          NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS256).build();
      return decoder.decode(token);
    } catch (ParseException | JOSEException e) {
      throw new AppException(ErrorCode.UNAUTHENTICATED);
    }
  }
}
```

#### Sau khi cải thiện (`CustomJwtDecoder.java`)
```java
@Component
public class CustomJwtDecoder implements org.springframework.security.oauth2.jwt.JwtDecoder {
  @Value("${security.jwt.access-secret}")
  private String accessSecretKey;
  private final AuthService authService;
  public CustomJwtDecoder(AuthService authService) {
    this.authService = authService;
  }
  @Override
  public Jwt decode(String token) {
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);
      String tokenType = (String) signedJWT.getJWTClaimsSet().getClaim("token_type");
      if (tokenType == null || !tokenType.equalsIgnoreCase("access")) {
        throw new AppException(ErrorCode.UNAUTHENTICATED);
      }
      var response = authService.introspect(IntrospectRequest.builder().accessToken(token).build());
      if (!response.isValid()) {
        throw new AppException(ErrorCode.UNAUTHENTICATED);
      }
      SecretKeySpec secretKeySpec = new SecretKeySpec(accessSecretKey.getBytes(), "HS256");
      NimbusJwtDecoder decoder =
          NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS256).build();
      return decoder.decode(token);
    } catch (ParseException | JOSEException e) {
      throw new AppException(ErrorCode.UNAUTHENTICATED);
    }
  }
}
```
### 🔁 **26. Đổi tên field để đúng chuẩn camelCase (`java:S116`)**

#### Trước khi cải thiện (`SecurityConfig.java`)
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  private final String[] PUBLIC_ENDPOINTS = {"/api/v1/uploads/**", "/api/v1/auth/**", "/ws/**"};

  private final CustomJwtDecoder jwtDecoder;

  public SecurityConfig(CustomJwtDecoder jwtDecoder) {
    this.jwtDecoder = jwtDecoder;
  }
  ...
```

#### Sau khi cải thiện (`SecurityConfig.java`)
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  private final String[] publicEndpoints = {"/api/v1/uploads/**", "/api/v1/auth/**", "/ws/**"};

  private final CustomJwtDecoder jwtDecoder;

  public SecurityConfig(CustomJwtDecoder jwtDecoder) {
    this.jwtDecoder = jwtDecoder;
  }
  ...
```

## 5. Đánh giá

- ✅ SonarQube giúp phát hiện **các lỗi tiềm ẩn và khuyến nghị cải thiện** rõ ràng.
- 🛠️ Thời gian sửa lỗi ước lượng giúp nhóm dev lên kế hoạch xử lý dễ hơn.
- 🚀 Sau khi xử lý 26 vấn đề, mã nguồn sạch hơn, dễ bảo trì hơn và chuẩn hóa theo Java convention.

---

## 6. Kết luận
Việc tích hợp SonarQube là cần thiết cho bất kỳ dự án nào muốn:
- Nâng cao chất lượng mã nguồn
- Cải thiện độ ổn định và bảo mật
- Giảm chi phí bảo trì dài hạn
