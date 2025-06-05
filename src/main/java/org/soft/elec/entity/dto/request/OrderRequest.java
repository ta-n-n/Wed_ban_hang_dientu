package org.soft.elec.entity.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
  @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "{email.invalid}")
  private String customerEmail;

  @Size(max = 20, message = "phone.maxsize")
  private String customerPhone;

  @Size(max = 50, message = "firstName.maxsize")
  private String customerFirstName;

  @Size(max = 50, message = "lastName.maxsize")
  private String customerLastName;

  private String billingFirstName;
  private String billingLastName;
  private String billingAddress1;
  private String billingAddress2;
  private String billingCity;
  private String billingState;
  private String billingZip;
  private String billingCountry;

  private String shippingFirstName;
  private String shippingLastName;
  private String shippingAddress1;
  private String shippingAddress2;
  private String shippingCity;
  private String shippingState;
  private String shippingZip;
  private String shippingCountry;

  private BigDecimal subTotal;
  private String shippingMethod;
  private BigDecimal shippingCost;
  private Long couponId;
  private BigDecimal discount;
  private BigDecimal total;

  private String paymentMethod;
  private String currency;
  private BigDecimal currencyRate;
  private String locale;
  private String status;

  private String note;
}
