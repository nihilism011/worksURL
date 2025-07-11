package dev.hastore.worksURL.dto;

import dev.hastore.worksURL.entity.DetectedUrl;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaliciousUrlResDto {
    private Long id;
    private DetectedUrl detectedUrl;
    private String distinction;
    private String detectionName;
    private boolean addVirus;
}
