package com.example.emailnotification.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailSendDetailsRequest {
    private String recipient;
    private String subject;
    private String messageBody;
}
