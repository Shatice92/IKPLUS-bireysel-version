package org.hatice.ikplus.service.usermanagement;

import lombok.RequiredArgsConstructor;
import org.hatice.ikplus.repository.usermanagement.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
}
