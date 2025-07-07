package org.soft.elec.entity.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "order_product_variations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductVariation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_product_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  private OrderProduct orderProduct;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "variation_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  private Variation variation;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "value", nullable = false)
  private String value;

  @CreationTimestamp
  @Column(name = "updated_at", updatable = false)
  private LocalDateTime updatedAt;

  @UpdateTimestamp private LocalDateTime createdAt;
}
