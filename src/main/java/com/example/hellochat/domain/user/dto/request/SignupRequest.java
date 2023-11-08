package com.example.hellochat.domain.user.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String explain;

    @NotNull
    private Integer birth;

    @Getter
    private boolean admin = false;
    private String adminToken = "";
}
