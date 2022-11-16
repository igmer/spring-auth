package com.template.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErroDto {
    private String code;
    private  String message;
}
