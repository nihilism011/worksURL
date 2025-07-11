package dev.hastore.worksURL.service;

import dev.hastore.worksURL.constant.RegexPatterns;
import dev.hastore.worksURL.dto.DetectUrlReqDto;
import dev.hastore.worksURL.dto.DetectedUrlResDto;
import dev.hastore.worksURL.dto.MaliciousUrlResDto;
import dev.hastore.worksURL.entity.DetectedUrl;
import dev.hastore.worksURL.entity.MaliciousUrl;
import dev.hastore.worksURL.mapper.DetectedUrlMapper;
import dev.hastore.worksURL.repository.MaliciousUrlRepository;
import dev.hastore.worksURL.repository.UrlQueryRepository;
import dev.hastore.worksURL.repository.WorksUrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class WorksUrlService {

    private static final Pattern SPLIT_BY_DATE_PATTERN = Pattern.compile("(?=\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final WorksUrlRepository worksUrlRepository;
    private final UrlQueryRepository urlQueryRepository;
    private final MaliciousUrlRepository maliciousUrlRepository;
    private final DetectedUrlMapper detectedUrlMapper;

    public static List<String> extractLogEntries(String multiLineLog) {
        String[] rawLogEntries = SPLIT_BY_DATE_PATTERN.split(multiLineLog);
        List<String> cleanedLogEntries = new ArrayList<>();

        for (String entry : rawLogEntries) {
            String trimmedEntry = entry.trim();
            if (!trimmedEntry.isEmpty()) {
                cleanedLogEntries.add(trimmedEntry);
            }
        }
        return cleanedLogEntries;
    }

    public int toUrlList(String copyString) {
        String cleanLog = logCleaner(copyString);
        List<String> logEntries = extractLogEntries(cleanLog);
        List<DetectedUrl> detectedUrls = refineList(logEntries);
        worksUrlRepository.saveAll(detectedUrls);
        System.out.println(detectedUrls.size()+"개 URL 등록.");
        return detectedUrls.size();
    }

    private String logCleaner(String messageListString) {
        String resultString = messageListString;
        for (RegexPatterns pattern : RegexPatterns.values()) {
            resultString = resultString.replaceAll(pattern.getPatternString(), "");
        }

        return resultString;
    }

    private List<DetectedUrl> refineList(List<String> logEntries) {
        List<DetectedUrl> list = new ArrayList<>();
        for (String log : logEntries) {
            LocalDateTime dateTime = LocalDateTime.parse(log.substring(0, 16), formatter);
            String[] urlString = log.substring(17).split(" ", 2);
            String urlName = urlString[0];
            String issueName = "Non Info";
            if (urlString.length > 1) {
                issueName = urlString[1];
            }
            DetectedUrl item = DetectedUrl.builder()
                    .issuedAt(dateTime)
                    .url(urlName)
                    .issue(issueName.trim())
                    .processed(false)
                    .malicious(false)
                    .build();
            list.add(item);
        }
        return list;
    }

    public List<DetectedUrlResDto> getAllDectedUrlList() {
        return detectedUrlMapper.detectedUrlToResDto(worksUrlRepository.findAll());
    }
    public List<DetectedUrlResDto> getUrlList(String url, String issue, Boolean processed, Boolean malicious){
        List<DetectedUrl> eList = urlQueryRepository.findSearchUrls(url,issue,processed,malicious);
        detectedUrlMapper.detectedUrlToResDto(eList);
        return detectedUrlMapper.detectedUrlToResDto(eList);
    }
    public void updateMaliciousUrl(DetectUrlReqDto detectInfoReqDto){
         DetectedUrl detectedUrl = worksUrlRepository.findById(detectInfoReqDto.getId()).orElseThrow();
        detectedUrl.setMalicious();
        MaliciousUrl maliciousUrl = MaliciousUrl.builder()
                .detectedUrl(detectedUrl)
                .addVirus(false)
                .distinction(detectInfoReqDto.getDistinction())
                .detectionName(detectInfoReqDto.getDetection_name())
                .build();
        maliciousUrlRepository.save(maliciousUrl);
    }
    public void urlInfoReset(Long id){
        DetectedUrl detectedUrl = worksUrlRepository.findById(id).orElseThrow();
        detectedUrl.infoReset();
        worksUrlRepository.save(detectedUrl);
        if(maliciousUrlRepository.findById(id).isPresent()){
            maliciousUrlRepository.deleteById(id);
        };
    }
    public void setUrlInfoNormal(Long id){
        DetectedUrl detectedUrl = worksUrlRepository.findById(id).orElseThrow();
        detectedUrl.setNormal();
        worksUrlRepository.save(detectedUrl);
        if(maliciousUrlRepository.findById(id).isPresent()){
            maliciousUrlRepository.deleteById(id);
        }
    }
    public List<MaliciousUrlResDto> getMaliciousUrlList(){

        return detectedUrlMapper.MaliciousUrlToResDto(maliciousUrlRepository.findAll());
    }
}
