package com.s3.t.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter @Setter
public class ErrorResponse {

    @Schema(example = "400")
    private String statusCode;

    @Schema(example = "Something went wrong.")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(example = "Errors.")
    private List<String> moreInfo;
}
