package org.soft.elec.specification;

import org.soft.elec.entity.models.EntityFile;
import org.springframework.data.jpa.domain.Specification;

public class EntityFileSpecification {
  private EntityFileSpecification() {}
  public static Specification<EntityFile> hasEntityId(Integer entityId) {
    return (root, query, cb) -> entityId == null ? null : cb.equal(root.get("entityId"), entityId);
  }
  public static Specification<EntityFile> hasEntityType(String entityType) {
    return (root, query, cb) ->
        (entityType == null || entityType.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("entityType")), entityType.toLowerCase());
  }
  public static Specification<EntityFile> hasZone(String zone) {
    return (root, query, cb) ->
        (zone == null || zone.isBlank())
            ? null
            : cb.equal(cb.lower(root.get("zone")), zone.toLowerCase());
  }
}
