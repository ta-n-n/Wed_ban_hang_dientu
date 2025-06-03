package org.soft.elec.entity.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponse {
    private Integer id;
    private Integer userId;
    private String filename;
    private String disk;
    private String path;
    private String extension;
    private String mime;
    private String size;

}
