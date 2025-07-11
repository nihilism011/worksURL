package dev.hastore.worksURL.mapper;

import dev.hastore.worksURL.dto.DetectedUrlResDto;
import dev.hastore.worksURL.dto.MaliciousUrlResDto;
import dev.hastore.worksURL.entity.DetectedUrl;
import dev.hastore.worksURL.entity.MaliciousUrl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DetectedUrlMapper {
    public List<DetectedUrlResDto> detectedUrlToResDto(List<DetectedUrl> detectedUrlList){
        return detectedUrlList.stream().map(urlInfo -> DetectedUrlResDto.builder()
                        .id(urlInfo.getId())
                        .url(urlInfo.getUrl())
                        .issue(urlInfo.getIssue())
                        .issuedAt(urlInfo.getIssuedAt())
                        .processed(urlInfo.getProcessed())
                        .malicious(urlInfo.getMalicious())
                        .build())
                .toList();
    }
    public List<MaliciousUrlResDto> MaliciousUrlToResDto(List<MaliciousUrl> maliciousUrlList){
        return maliciousUrlList.stream().map(urlInfo -> MaliciousUrlResDto.builder()
                .id(urlInfo.getId())
                .detectedUrl(urlInfo.getDetectedUrl())
                .distinction(urlInfo.getDistinction())
                .detectionName(urlInfo.getDetectionName())
                .addVirus(urlInfo.getAddVirus())
                .build())
            .toList();

    }
}
