package org.soft.elec.entity.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    private Long customerId;
    @Column(name = "customer_amail", length = 100)
    private String customerEmail;

    @Column(name = "customer_phone", length = 20)
    private String customerPhone;

    @Column(name = "customer_first_name",length = 50)
    private String customerFirstName;

    @Column(name = "customer_last_name", length = 50)
    private String customerLastName;

    @Column(name = "billing_first_name")
    private String billingFirstName;

    @Column(name = "billing_last_name")
    private String billingLastName;

    @Column(name = "billing_address1")
    private String billingAddress1;

    @Column(name = "billing_address2")
    private String billingAddress2;

    @Column(name = "billing_city")
    private String billingCity;

    @Column(name = "billing_state")
    private String billingState;

    @Column(name = "billing_zip")
    private String billingZip;

    @Column(name = "billing_country")
    private String billingCountry;

    @Column(name = "shipping_first_name")
    private String shippingFirstName;

    @Column(name = "shipping_last_name")
    private String shippingLastName;

    @Column(name = "shipping_address_1")
    private String shippingAddress1;

    @Column(name = "shipping_address_2")
    private String shippingAddress2;

    @Column(name = "shipping_city")
    private String shippingCity;

    @Column(name = "shipping_state")
    private String shippingState;

    @Column(name = "shipping_zip")
    private String shippingZip;

    @Column(name = "shipping_country")
    private String shippingCountry;

    @Column(name = "sub_total", precision = 18, scale = 4)
    private BigDecimal subTotal = BigDecimal.ZERO;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "shipping_cost", precision = 18, scale = 4)
    private BigDecimal shippingCost = BigDecimal.ZERO;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "discount", precision = 18, scale = 4)
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(name = "total", precision = 18, scale = 4)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "currency")
    private String currency;

    @Column(precision = 18, scale = 4)
    private BigDecimal currencyRate = BigDecimal.ONE;

    @Column(name = "locale")
    private String locale;

    @Column(name = "status")
    private String status;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
