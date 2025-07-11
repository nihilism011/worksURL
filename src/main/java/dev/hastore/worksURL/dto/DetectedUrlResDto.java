package dev.hastore.worksURL.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetectedUrlResDto {

    private Long id;
    private LocalDateTime issuedAt;
    private String url;
    private String issue;
    private Boolean processed ;
    private Boolean malicious ;
}
