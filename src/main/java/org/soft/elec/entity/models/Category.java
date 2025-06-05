package org.soft.elec.entity.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Category parent;

  @OneToMany(mappedBy = "parent")
  private List<Category> children;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "position")
  private Integer position;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToMany(mappedBy = "categories")
  private List<Product> products;
}
