package org.hatice.ikplus.dto.response.userresponse;

import org.hatice.ikplus.enums.RoleName;

import java.util.List;

public record RoleResponse(RoleName name, List<String> permissions) {
}