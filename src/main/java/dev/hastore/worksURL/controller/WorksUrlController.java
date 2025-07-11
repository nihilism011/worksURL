package dev.hastore.worksURL.controller;

import dev.hastore.worksURL.dto.*;
import dev.hastore.worksURL.service.WorksUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/urls")
public class WorksUrlController {

    private final WorksUrlService service;

    @GetMapping("")
    public ResponseEntity<List<DetectedUrlResDto>> searchUrlList(
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "issue", required = false) String issue,
            @RequestParam(value = "processed", required = false) Boolean processed,
            @RequestParam(value = "malicious", required = false) Boolean malicious
    ) {
        System.out.println("url = " + url);
        System.out.println("issue = " + issue);
        System.out.println("processed = " + processed);
        System.out.println("malicious = " + malicious);

        return ResponseEntity.ok(service.getUrlList(url,issue,processed,malicious));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> getWorksString(@RequestBody RequestWorksString urlListString){
        int listLength = service.toUrlList(urlListString.getUrlListString());
        return ResponseEntity.ok(ResponseMessage.success(String.valueOf(listLength)));
    }
    @PostMapping("detect")
    public ResponseEntity<ResponseMessage> detectUrlUpdate(@RequestBody DetectUrlReqDto urlInfoReqDto){
        System.out.println(urlInfoReqDto);
        service.updateMaliciousUrl(urlInfoReqDto);
        return ResponseEntity.ok(ResponseMessage.success("success"));
    }
    @PutMapping("detect/cancel/{id}")
    public ResponseEntity<ResponseMessage> urlInfoReset(@PathVariable("id") Long id){
        service.urlInfoReset(id);
        return ResponseEntity.ok(ResponseMessage.success("success"));
    }
    @PutMapping("detect/normal/{id}")
    public ResponseEntity<ResponseMessage> setInfoNormal(@PathVariable("id") Long id){
        service.setUrlInfoNormal(id);
        return ResponseEntity.ok(ResponseMessage.success("success"));
    }

    @GetMapping("all")
    public ResponseEntity<List<DetectedUrlResDto>> getUrlInfoList(){
        return ResponseEntity.ok(service.getAllDectedUrlList());
    }

    @GetMapping("malicious")
    public ResponseEntity<List<MaliciousUrlResDto>> getMaliciousUrlList(){
        return ResponseEntity.ok(service.getMaliciousUrlList());
    }
}
