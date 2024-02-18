package com.yarvin.auth_service.store.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto extends Throwable {
    private String message;


}
