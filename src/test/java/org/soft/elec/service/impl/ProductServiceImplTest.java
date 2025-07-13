package org.soft.elec.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.soft.elec.entity.dto.request.ProductRequest;
import org.soft.elec.entity.dto.response.ProductResponse;
import org.soft.elec.entity.models.*;
import org.soft.elec.exception.AppException;
import org.soft.elec.exception.ErrorCode;
import org.soft.elec.repository.*;
import org.soft.elec.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.soft.elec.entity.mapper.ProductMapper;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private VariationRepository variationRepository;

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private ProductVariantRepository productVariantRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Kiểm tra môi trường test được thiết lập
    }

    // Kiểm tra tạo sản phẩm mới
    @Test
    public void testCreateProduct() {
        ProductRequest request = new ProductRequest();
        request.setBrandId(1);
        Product product = new Product();
        ProductResponse response = new ProductResponse();
        response.setId(1);

        when(productMapper.toEntity(request)).thenReturn(product);
        when(brandRepository.findById(1)).thenReturn(Optional.of(new Brand()));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toResponse(product)).thenReturn(response);

        ProductResponse result = productService.createProduct(request);
        assertEquals(1, result.getId());
    }

    // Kiểm tra cập nhật sản phẩm
    @Test
    public void testUpdateProduct() {
        ProductRequest request = new ProductRequest();
        request.setBrandId(1);
        Product product = new Product();
        product.setId(1);
        ProductResponse response = new ProductResponse();
        response.setId(1);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(brandRepository.findById(1)).thenReturn(Optional.of(new Brand()));
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toResponse(product)).thenReturn(response);

        ProductResponse result = productService.updateProduct(1, request);
        assertEquals(1, result.getId());
    }

    // Kiểm tra xóa sản phẩm
    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setId(1);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        productService.deleteProduct(1);
        // Kiểm tra không ném exception
    }

    // Kiểm tra lấy sản phẩm theo ID
    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1);
        ProductResponse response = new ProductResponse();
        response.setId(1);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(productMapper.toResponse(product)).thenReturn(response);

        ProductResponse result = productService.getProductById(1);
        assertEquals(1, result.getId());
    }

    // Kiểm tra lỗi khi sản phẩm không tồn tại
    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(AppException.class, () -> productService.getProductById(1),
                "Nên ném lỗi PRODUCT_NOT_FOUND");
    }
    //
    /**
     * Test lỗi khi tạo sản phẩm với brandId không tồn tại
     * Giả lập: brandRepository không tìm thấy brand theo ID
     * Kết quả: Phải ném ra AppException với mã lỗi BRAND_NOT_FOUND
     */
    @Test
    public void testCreateProductWithInvalidBrandId() {
        ProductRequest request = new ProductRequest();
        request.setBrandId(99); // brandId không tồn tại

        Product product = new Product();

        // Giả lập chuyển request -> entity
        when(productMapper.toEntity(request)).thenReturn(product);

        // Giả lập không tìm thấy brand trong DB
        when(brandRepository.findById(99)).thenReturn(Optional.empty());

        // Kiểm tra có ném ra AppException với mã lỗi đúng hay không
        AppException exception = assertThrows(AppException.class, () -> {
            productService.createProduct(request);
        });

        assertEquals(ErrorCode.BRAND_NOT_FOUND, exception.getErrorCode());
    }

    /**
     *  Test lỗi khi xóa sản phẩm không tồn tại
     * Giả lập: không tìm thấy sản phẩm theo ID
     * Kết quả: phải ném ra AppException với mã lỗi PRODUCT_NOT_FOUND
     */
    @Test
    public void testDeleteProduct_NotFound() {
        // Giả lập không tìm thấy sản phẩm theo ID
        when(productRepository.findById(123)).thenReturn(Optional.empty());

        // Kiểm tra ném đúng loại exception với mã lỗi
        AppException exception = assertThrows(AppException.class, () -> {
            productService.deleteProduct(123);
        });

        assertEquals(ErrorCode.PRODUCT_NOT_FOUND, exception.getErrorCode());
    }

}