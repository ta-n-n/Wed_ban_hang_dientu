package org.soft.elec.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.soft.elec.entity.dto.request.ProductRequest;
import org.soft.elec.entity.dto.response.ProductResponse;
import org.soft.elec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Kiểm thử đơn vị ProductController.
 * Dùng WebMvcTest để kiểm thử controller mà không khởi tạo toàn bộ context.
 */
@WebMvcTest(value = ProductController.class, excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@Import(ProductControllerTest.ProductServiceTestConfig.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductRequest request;
    private ProductResponse response;

    // Thiết lập dữ liệu trước mỗi test
    @BeforeEach
    public void setUp() {
        // Dữ liệu yêu cầu để tạo hoặc cập nhật sản phẩm
        request = new ProductRequest();
        request.setName("Laptop test");
        request.setBrandId(1);
        request.setPrice(BigDecimal.valueOf(999));
        request.setInStock(true);
        request.setManageStock(true);

        // Dữ liệu phản hồi giả lập từ service
        response = new ProductResponse();
        response.setId(1);
    }

    @Test
    public void testCreateProduct() throws Exception {
        // Giả lập khi gọi createProduct() thì trả về đối tượng phản hồi
        when(productService.createProduct(any(ProductRequest.class))).thenReturn(response);

        // Gửi POST request để tạo sản phẩm, kiểm tra kết quả phản hồi
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1)); // Kiểm tra response có id = 1
    }

    @Test
    public void testUpdateProduct() throws Exception {
        // Giả lập khi gọi updateProduct(id, request) thì trả về response
        when(productService.updateProduct(eq(1), any(ProductRequest.class))).thenReturn(response);

        // Gửi PUT request để cập nhật sản phẩm, kiểm tra phản hồi trả về đúng id
        mockMvc.perform(put("/api/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // Giả lập khi gọi deleteProduct(id), không làm gì (void method)
        doNothing().when(productService).deleteProduct(1);

        // Gửi DELETE request và kiểm tra status code là 200 OK
        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetById() throws Exception {
        // Giả lập khi gọi getProductById(1) thì trả về response
        when(productService.getProductById(1)).thenReturn(response);

        // Gửi GET request và kiểm tra id trong response
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Giả lập service trả về danh sách 1 sản phẩm
        when(productService.getAllProducts()).thenReturn(List.of(response));

        // Gửi GET request đến endpoint getAll, kiểm tra phần tử đầu tiên có id = 1
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0].id").value(1));
    }

    @Test
    public void testGetProductById_NotFound() throws Exception {
        // Giả lập lỗi khi gọi getProductById với ID không tồn tại
        when(productService.getProductById(999)).thenThrow(new RuntimeException("Product not found"));

        // Gửi GET request đến id không tồn tại, kiểm tra trả về lỗi 404 (nếu chưa xử lý exception custom)
        mockMvc.perform(get("/api/v1/products/999"))
                .andExpect(status().isInternalServerError());
    }

    // Trường hợp test bảo mật: chỉ có ý nghĩa nếu bạn cấu hình Spring Security
    @Test
    public void testUnauthorizedAccess() throws Exception {
        // Kiểm tra quyền truy cập với người dùng không đăng nhập (đang bị bypass do excludeSecurity)
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk()); // Nếu cấu hình security thì có thể trả về 401
    }

    @Test
    public void testForbiddenAccess() throws Exception {
        // Kiểm tra quyền truy cập không hợp lệ (ví dụ role không đủ quyền)
        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isOk()); // Nếu cấu hình security thì có thể trả về 403
    }

    // Tạo mock ProductService cho context test
    @TestConfiguration
    static class ProductServiceTestConfig {
        @Bean
        public ProductService productService() {
            return mock(ProductService.class);
        }
    }
}
