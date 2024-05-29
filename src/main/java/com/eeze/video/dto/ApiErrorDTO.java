package com.eeze.video.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorDTO {

    private Integer code;

    private String message;
}
