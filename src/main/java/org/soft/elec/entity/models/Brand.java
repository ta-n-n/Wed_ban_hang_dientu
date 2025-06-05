package org.soft.elec.entity.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive; // 0:Disable　1: Enable

  @Column(name = "file_logo")
  private String fileLogo;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "brand")
  private List<Product> products;
}
