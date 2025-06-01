package org.soft.elec.entity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "disk", nullable = false)
    private String disk;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "mime", nullable = false)
    private String mime;

    @Column(name = "size", nullable = false)
    private String size;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
