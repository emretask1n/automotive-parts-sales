package com.emretaskin.itg.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String jwtToken;
    private String nameSurname;
    private Long id;

}
