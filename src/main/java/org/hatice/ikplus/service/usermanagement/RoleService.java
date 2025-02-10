package org.hatice.ikplus.service.usermanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.usermanagement.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
}
