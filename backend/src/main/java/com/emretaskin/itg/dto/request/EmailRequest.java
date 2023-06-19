package com.emretaskin.itg.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private String subject;
    private String body;
}
