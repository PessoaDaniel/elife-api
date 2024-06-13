package com.dansales.elife.elifeapi.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data()
public class EmailDTO {
    @NotBlank
    private String owner;
    @NotBlank
    private String from;
    @NotBlank
    private String subject;
    @NotBlank
    private String to;
    @NotBlank
    private String content;
}
