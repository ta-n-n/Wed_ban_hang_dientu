package org.soft.elec.entity.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "variation_values")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariationValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uid;

    @ManyToOne
    @JoinColumn(name = "variation_id")
    private Variation variation;

    private String value;

    private Integer position;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
