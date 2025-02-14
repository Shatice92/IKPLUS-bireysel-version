package org.hatice.ikplus.dto.request.userrequest;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UpdateRoleRequestDto(@NotBlank  List<String> permissions) {
}