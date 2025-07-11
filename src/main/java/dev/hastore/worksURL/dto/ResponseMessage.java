package dev.hastore.worksURL.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {

    private String status;
    private String message;

    public static ResponseMessage success(String message){
        return ResponseMessage.builder()
                .status("success")
                .message(message)
                .build();
    }
}
