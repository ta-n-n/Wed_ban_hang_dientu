package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.soft.elec.entity.models.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityFileResponse {
    private Integer id;
    private File file;
    private Integer entityId;
    private String entityType;
    private String zone;
}
