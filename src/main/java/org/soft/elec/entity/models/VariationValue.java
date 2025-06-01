package org.soft.elec.entity.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "label", nullable = false)
    private String label;

    @ManyToOne
    @JoinColumn(name = "variation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Variation variation;

    @Column(name = "value")
    private String value;

    @Column(name = "position")
    private Integer position;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
