package org.soft.elec.entity.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "option_values")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;

    @Column(precision = 18, scale = 4)
    private BigDecimal price;

    @Column(name = "price_type")
    private String priceType;

    private Integer position;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}