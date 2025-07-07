package org.soft.elec.specification;

import org.soft.elec.entity.models.File;
import org.springframework.data.jpa.domain.Specification;

public class FileSpecification {

  public static Specification<File> hasUserId(Integer userId) {
    return (root, query, cb) -> userId == null ? null : cb.equal(root.get("userId"), userId);
  }

  public static Specification<File> hasFilename(String filename) {
    return (root, query, cb) ->
        (filename == null || filename.isBlank())
            ? null
            : cb.like(cb.lower(root.get("filename")), "%" + filename.toLowerCase() + "%");
  }

  public static Specification<File> hasMime(String mime) {
    return (root, query, cb) ->
        (mime == null || mime.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("mime")), mime.toLowerCase());
  }

  public static Specification<File> hasExtension(String extension) {
    return (root, query, cb) ->
        (extension == null || extension.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("extension")), extension.toLowerCase());
  }
}
