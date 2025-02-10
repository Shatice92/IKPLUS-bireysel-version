package org.hatice.ikplus.controller.usermanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.constant.Endpoints;
import org.hatice.ikplus.service.usermanagement.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.ROLE)
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
}
