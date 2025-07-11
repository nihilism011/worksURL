package dev.hastore.worksURL.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetectUrlReqDto {
    private Long id;
    private String distinction;
    private String detection_name;
}
